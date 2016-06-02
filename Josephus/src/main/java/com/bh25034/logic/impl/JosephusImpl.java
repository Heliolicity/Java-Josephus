package com.bh25034.logic.impl;

import com.bh25034.logic.Josephus;

public class JosephusImpl implements Josephus {

	public JosephusImpl() {}
	
	public int determinePosition(int n, int k) {
		
		int pos = 0;
		
		if (n == 1) pos = 1;
		else pos = (determinePosition(n - 1, k) + (k - 1)) % n + 1;
		
		return pos;
		
	}
	
}
