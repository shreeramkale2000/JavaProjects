package jain.ritvik.utils.session;

public class SessionBO {

	String SessionID = null;
	String IPAddress = null;
	java.util.Date loginAt = null;
	
	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public java.util.Date getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(java.util.Date loginAt) {
		this.loginAt = loginAt;
	}
	
	
	
}
