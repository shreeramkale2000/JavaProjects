package org.ritvik.utils.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.ritvik.utils.common.Constants;
import org.ritvik.utils.exceptions.MaxUsersExceededException;

public class SessionManager {

	private static SessionManager sessionManager = null;

	private ConcurrentHashMap<String, String> users = null;
	private ConcurrentHashMap<String, String> sessions = null;
	private ConcurrentHashMap<String, SessionBO> sessionInfo = null;
	private Semaphore available = null;
	private int MAX_USERS = 99999;
	private long MAX_WAIT_TIME_TO_ACQUIRE = 2000;
	private String sUser = null;
	
	private SessionManager() {
		users = new ConcurrentHashMap<String, String>();
		sessions = new ConcurrentHashMap<String, String>();
		sessionInfo = new ConcurrentHashMap<String, SessionBO>();
	}
	
	public static SessionManager getInstance() {

		if (sessionManager == null) {
			sessionManager = new SessionManager();
		}

		return sessionManager;
	}

	public void setMaxUsers(int maxUsers) {
		MAX_USERS = maxUsers;
	}

	public void setMaxWaitTime(int WaitTime) {
		MAX_WAIT_TIME_TO_ACQUIRE = WaitTime;
	}

	public boolean registerUser(String sUser, String sSessionID, SessionBO session)
			throws InterruptedException, MaxUsersExceededException {

		if (available == null) {
			available = new Semaphore(MAX_USERS, true);
		}

		try {

			if (!checkDuplicateLogin(sUser, sSessionID)) {
				if (available.tryAcquire(1, MAX_WAIT_TIME_TO_ACQUIRE,
						TimeUnit.MILLISECONDS)) {
					users.putIfAbsent(sUser, sSessionID);
					sessions.put(sSessionID, sUser);
					sessionInfo.put(sUser, session);

					return Constants.SUCCESS;
				} else {
					throw new MaxUsersExceededException(
							"Max number of user exceeded ");
				}
			} else {
				return Constants.FAILED;
			}
		} finally {
			
		}
	}

	public boolean checkDuplicateLogin(String sUser, String sSessionID) {

		boolean check = false;
		
		if (users.containsKey(sUser)) {
			return Constants.SUCCESS;
		} else {
			check = Constants.FAILED;
		}
		
		if (sessions.containsKey(sSessionID)) {
			return Constants.SUCCESS;
		} else {
			check = Constants.FAILED;
		}
		
		return check;
	}

	public boolean removeUserSession(String sSessionID) {

		if (available == null) {
			available = new Semaphore(MAX_USERS, true);
		}

		try {

			if (sessions.containsKey(sSessionID)) {
				sUser = getUserBySessionID(sSessionID);
				users.remove(sUser);
				sessions.remove(sSessionID);
				sessionInfo.remove(sUser);
				sUser = null;
				
				available.release();
				
				return Constants.SUCCESS;
			}

		} finally {
			
		}

		return Constants.FAILED;
	}

	public String getUserBySessionID(String sSessionID){
		return sessions.get(sSessionID).toString();
	}
	
	public ConcurrentHashMap<String, SessionBO> getLoggedInUsers(){
		return this.sessionInfo;
	}
	
}
