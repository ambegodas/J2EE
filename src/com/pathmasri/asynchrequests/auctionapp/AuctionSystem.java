/**
 * @autor pathmasri
 * May 30, 2015 3:43:24 PM
 */
package com.pathmasri.asynchrequests.auctionapp;

import java.util.Hashtable;

public class AuctionSystem {
	
	private Hashtable<String,AuctionItem> items;
	
	public AuctionSystem(){
		
		items = new Hashtable<String,AuctionItem>();
		String [] itemsCodes = {"AA","AB","AC"};
		
		for(String s: itemsCodes){
			AuctionItem item = new AuctionItem(s);
			items.put(s, item);
			new Thread(item).start();
		}
	}
	
	public AuctionItem findItem(String key){
		return items.get(key);
	}
	

}
