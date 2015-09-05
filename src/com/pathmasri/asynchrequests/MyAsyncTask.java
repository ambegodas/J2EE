/**
 * @autor pathmasri
 * May 30, 2015 11:57:46 AM
 */
package com.pathmasri.asynchrequests;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class MyAsyncTask implements Runnable{
	
	private AsyncContext actxt;
	

	/**
	 * @return the actxt
	 */
	public AsyncContext getActxt() {
		return actxt;
	}


	/**
	 * @param actxt the actxt to set
	 */
	public void setActxt(AsyncContext actxt) {
		this.actxt = actxt;
	}


	public MyAsyncTask(AsyncContext actxt) {
		this.actxt = actxt;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	
		try{
		Thread.sleep(6000);
		ServletResponse response = actxt.getResponse();
		response.getWriter().write("Async Request Processed");
		response.flushBuffer();
		actxt.complete();
		} catch (Exception e){
			
		}
		
	}

}
