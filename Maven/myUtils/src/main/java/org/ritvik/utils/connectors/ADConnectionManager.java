package org.ritvik.utils.connectors;

import java.io.Serializable;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ADConnectionManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Logger log = null;
	private DirContext ctx = null;
	private String initContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	private String providerUrl = "";
	private String securityPrincipal = "";
	private String searchBase = "";
	
	private String sUser = "";
	private String sPassword = "";
	private String sEmpCode = "";

	private String sFullName = "";
	private String sEmailAddress = "";
	private String sDepartment = "";

	Hashtable<String, String> env = null;

	public ADConnectionManager() {
		
		//log = new ObjectFactory().getLoggerObject(ADConnectionManager.class);
		
	}

	public String getInitContextFactory() {
		return initContextFactory;
	}

	public void setInitContextFactory(String initContextFactory) {
		this.initContextFactory = initContextFactory;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}
	
	public String getSearchBase() {
		return searchBase;
	}

	public void setSearchBase(String searchBase) {
		this.searchBase = searchBase;
	}

	public String getSecurityPrincipal() {
		return securityPrincipal;
	}

	public void setSecurityPrincipal(String securityPrincipal) {
		this.securityPrincipal = (securityPrincipal.startsWith("@") ? securityPrincipal : "@" + securityPrincipal);
	}

	private String getUser() {
		return sUser;
	}

	public void setUser(String sEmpCode) {
		this.sUser = sEmpCode;
	}

	private String getPassword() {
		return sPassword;
	}

	public void setPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public String getsFullName() {
		return sFullName;
	}

	public void setsFullName(String sFullName) {
		this.sFullName = sFullName;
	}

	public String getsEmpCode() {
		return sEmpCode;
	}

	public void setsEmpCode(String sEmpCode) {
		this.sEmpCode = sEmpCode;
	}
	
	public String getsEmailAddress() {
		return sEmailAddress;
	}

	public void setsEmailAddress(String sEmailAddress) {
		this.sEmailAddress = sEmailAddress;
	}

	public String getsDepartment() {
		return sDepartment;
	}

	public void setsDepartment(String sDepartment) {
		this.sDepartment = sDepartment;
	}

	public boolean authenticate() {

		boolean flag = false;
		//log.debug("INITIAL_CONTEXT_FACTORY = " + initContextFactory);
		//log.debug("PROVIDER_URL = " + getProviderUrl());
		//log.debug("SECURITY_PRINCIPAL = " + getUser() + getSecurityPrincipal());
		
		//System.out.println(">> " + getUser() + (getSecurityPrincipal().startsWith("@") ? getSecurityPrincipal() : "@" + getSecurityPrincipal()));
		env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, initContextFactory);
		env.put(Context.PROVIDER_URL, getProviderUrl());
		env.put(Context.SECURITY_PRINCIPAL, getUser() + getSecurityPrincipal());
		env.put(Context.SECURITY_CREDENTIALS, getPassword());
		env.put(Context.REFERRAL, "follow");

		try {
			ctx = new InitialDirContext(env);
			//log.debug("flag --> " + ctx);

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			//constraints.setReturningAttributes(attrsID);
			StringBuffer searchFilter = new StringBuffer();
			//searchFilter.append("(&(objectClass=user)(|(userPrincipalName=");
			searchFilter.append("(&(objectClass=user)(|(samAccountName=");
			
			if(getsEmpCode().length() > 0){
				searchFilter.append(getsEmpCode());
			} else {
				searchFilter.append(getUser());
			}
			
			//searchFilter.append(getSecurityPrincipal());
			searchFilter.append(")))");
			// String searchFilter =
			// "(&(objectClass=user)(|(userPrincipalName="+getUser()+getSecurityPrincipal()+")))";

			NamingEnumeration<SearchResult> answer = ctx
					.search(getSearchBase().toString(), searchFilter.toString(),
							constraints);
			
			searchFilter.delete(0, searchFilter.length());
			
			//NamingEnumeration<String> at;
			
			
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next())
						.getAttributes();
				
				//at = attrs.getIDs();
				
				/*while(at.hasMoreElements()){
					
					attID = at.next();
					
					System.out.println(attID + " ---> " + attrs.get(attID));
				}*/
				
				System.out.println("attrs = " + attrs.getAll());
				/*try {
					log.debug("distinguishedName "
							+ attrs.get("distinguishedName").get());
				} catch (Exception ex) {

				}

				try {
					log.debug("givenname " + attrs.get("givenname").get());
				} catch (Exception ex) {

				}

				try {
					log.debug("sn " + attrs.get("sn").get());
				} catch (Exception ex) {

				}

				try {
					log.debug("mail " + attrs.get("mail").get());
				} catch (Exception ex){
					
				}

				try {
					log.debug("telephonenumber "
									+ attrs.get("telephonenumber"));
				} catch (Exception ex){
					
				}*/
				
				try {
					setsEmailAddress(attrs.get("mail").get().toString());
				} catch (Exception ex){
					setsEmailAddress("");
				}
				
				try {
					setsDepartment(attrs.get("department").get().toString());
				} catch (Exception ex){
					setsDepartment("");
				}
				
				try {
					searchFilter.append(attrs.get("givenname").get().toString().trim());
				} catch (Exception ex){
					searchFilter.append("");
				}
								
				searchFilter.append(" ");
								
				try {
					searchFilter.append(attrs.get("sn").get().toString().trim());
				} catch (Exception ex){
					searchFilter.append("");
				}
								
				setsFullName(searchFilter.toString());
				
				//log.debug("cn " + getsFullName());
				
				
			} else {
				System.out.println("user not found in Active Directory");
			}
			//System.out.println("flag --> " + flag);
			flag = true;
			
			ctx = null;

		} catch (NamingException e) {

			e.printStackTrace();
			
			//log.fatal(trace);
		} finally {
			setsEmpCode("");
			ctx = null;
			env = null;
			sUser = null;
			sPassword = null;
			securityPrincipal = null;
		}
		return flag;
	}

}
