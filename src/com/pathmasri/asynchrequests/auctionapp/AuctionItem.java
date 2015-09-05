/**
 * @autor pathmasri
 * May 30, 2015 3:45:26 PM
 */
package com.pathmasri.asynchrequests.auctionapp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class AuctionItem implements Runnable {

	private String itemRef;
	private int highestBid;
	private User bidder;
	private List<AsyncContext> pendingRequests;

	public AuctionItem(String ref) {
		this.itemRef = ref;
		this.pendingRequests = new ArrayList<AsyncContext>();
	}

	public int getHighestBid() {
		return this.highestBid;
	}

	public User getHighesBidder() {
		return this.bidder;
	}

	public void addPendingRequest(AsyncContext context) {
		synchronized (pendingRequests) {
			pendingRequests.add(context);
		}
	}

	public void updateBid(User bidder, int bid) {
		if (bid > highestBid) {
			synchronized (pendingRequests) {
				this.highestBid = bid;
				this.bidder = bidder;
				pendingRequests.notify();
			}
			
		}
	}

	@Override
	public void run() {
		synchronized (pendingRequests) {
			int lastBid = 0;
			
			while (true) {
				if (highestBid > lastBid) {
					
					while (!pendingRequests.isEmpty()) {
						AsyncContext ctx = pendingRequests.get(0);

						try {
							ServletResponse response = ctx.getResponse();
							PrintWriter output = response.getWriter();
							output.println(highestBid);
							output.println(bidder);
							ctx.complete();

						} catch (Exception e) {}
						
						pendingRequests.remove(0);						
					}
					lastBid = highestBid;					
				}
				try {
					pendingRequests.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			
		}
	}

}
