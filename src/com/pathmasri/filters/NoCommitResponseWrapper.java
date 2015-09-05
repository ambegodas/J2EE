/**
 * @autor pathmasri
 * May 27, 2015 11:12:50 PM
 */
package com.pathmasri.filters;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class NoCommitResponseWrapper extends HttpServletResponseWrapper {
	
	private ByteArrayOutputStream out;
	private ServletOutputStream outBytes;
	private PrintWriter outWriter;
	
	private class MyServletOutputStream extends ServletOutputStream{		
		public void write(int b){
			out.write(b);
		}
	}

	public void flushBuffer(){
		//Do nothing
	}
	
	public PrintWriter getWriter(){
		if(outWriter != null){
			return null;
		}
		if(outBytes != null){
			throw new IllegalStateException("getout already invoked");
		}
		out = new ByteArrayOutputStream();
		outWriter = new PrintWriter(out);
		return outWriter;
	}
	
	public ServletOutputStream getOutPutStream(){
		if(outBytes != null){
			return outBytes;
		}
		if(outWriter != null){
			throw new IllegalStateException("getWriter was invoked");
		}
		out = new ByteArrayOutputStream();
		outBytes = new MyServletOutputStream();
		return outBytes;
	}
	
	public ByteArrayOutputStream geOut(){
		return out;
	}
	
	
	
	public NoCommitResponseWrapper(HttpServletResponse response) {
		super(response);		
	}
	
}
