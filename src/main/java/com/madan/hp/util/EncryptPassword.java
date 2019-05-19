package com.madan.hp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class EncryptPassword {

	static String generatedPassword = null;

	private final static String trans = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
	   
	public final static synchronized String encrypt( String in ) {
		StringBuffer out = null;
	    if ( in != null && !"".equals( in ) ) {
	    	try {
	    		MessageDigest md = MessageDigest.getInstance( "SHA" );
	            md.update( in.getBytes() );
	            out = encode( md.digest() );
	        }
	        catch( NoSuchAlgorithmException e ) {
	                out = new StringBuffer( "x-x-x-x" );
	        }
	    }
        if(out != null)
        	return out.toString();
        return "";
	}
	
	public final static synchronized StringBuffer encode( byte in[] ) {
		StringBuffer out = new StringBuffer( );
        for (int i = 0; i < in.length; i = i + 3) {
            long num = 0;
            if ( i < in.length - 2 ) {
                num += 256 * 256 * Math.abs( in[i+2] );
            }
            if ( i < in.length - 1 ) {
                num += 256 * Math.abs( in[i+1] );
            }
            num += Math.abs( in[i] );
            for (int j = 0; j < 4; j++ ) {
                int k = new Long( 0x00003F & num ).intValue( );
                out.append( trans.charAt( k ) );
                num = num >> 3;
            }
        }
	    return out;
	}
	    
    public final static synchronized String encryptPartial( String in, int length, long seed ) {
        String temp = encrypt( in );
        String out = "";
        Random random = new Random( seed );
        for ( int i = 0; i < length; i++ ) {
            int j = random.nextInt( temp.length() );
            out += temp.substring( j, j+1 );
        }   
        return out;
    }
	    
    public final static synchronized boolean test( String candidate, String current ){
        return encrypt( candidate ).equals( current );
    }
}
