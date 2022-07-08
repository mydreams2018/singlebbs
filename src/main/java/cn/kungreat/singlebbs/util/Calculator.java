package cn.kungreat.singlebbs.util;

public final class Calculator {
	
	public static int count(String p){
		Integer one = null;
		String symbol = null;
		int symbolIndex = 0;
		Integer two = null;
		String symbol2 = null;
		byte[] bytes = p.getBytes();
		
		for(int x =0;x < bytes.length ;x++) {
			if('+'==(char)bytes[x] || '-'==(char)bytes[x]
					|| '*' == (char)bytes[x] || '/' == (char)bytes[x]) {
				if(one == null) {
					one = Integer.parseInt(new String(bytes,0,x));
					symbol = String.valueOf((char)bytes[x]);
					symbolIndex = x ;
				}else if(two == null) {
					two = Integer.parseInt(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					symbol2 = String.valueOf((char)bytes[x]);
					symbolIndex=x;
				}else {
					if("*".equals(symbol) || "/".equals(symbol)) {
						one = math(one,symbol,two);
						symbol = symbol2;
						two = Integer.parseInt(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					}else if("*".equals(symbol2) || "/".equals(symbol2)) {
						int three = Integer.parseInt(new String(bytes,symbolIndex+1,x-symbolIndex-1));
						two = math(two,symbol2,three);
					}else {
						one = math(one,symbol,two);
						symbol = symbol2;
						two = Integer.parseInt(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					}
					symbol2=String.valueOf((char)bytes[x]);
					symbolIndex = x ;
				}
			}
		}
		if(two == null) {
			return math(one,symbol,Integer.parseInt(new String(bytes,symbolIndex+1,bytes.length-symbolIndex-1)));
		}
		return math2(one,symbol,two,symbol2,Integer.parseInt(new String(bytes,symbolIndex+1,bytes.length-symbolIndex-1)));
	}
	
	
	private static int math2(int a,String sy1,int b,String sy2,int c) {
		if("+".equals(sy1)) {
			return a + math(b,sy2,c);
		}else if("-".equals(sy1)) {
			if("+".equals(sy2)) {
				return a - b + c;
			}
			return a - math(b,sy2,c);
		}else {
			return math(math(a,sy1,b),sy2,c);
		}
	}
	
	private static int math(int a,String symbol,int b) {
		if("+".equals(symbol)) {
			return a+b;
		}else if("-".equals(symbol)) {
			return a-b;
		}else if("*".equals(symbol)) {
			return a*b;
		}else {
			return a/b;
		}
	}
	
}