package gx;

import java.util.Scanner;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class polyval {
	public class pol{
		int coef = 1;
		int der = 0;
		ArrayList<String>  ch = new ArrayList<String>();
	}
	public class val{
		int val;
		String ch1;
	}
	public pol expression(String Exp){
		String num;
		pol  np = new pol();
		String rejust = "[a-z]";
		String rejust1 = "\\d*";
		Pattern p = Pattern.compile(rejust1);
		Pattern p1 = Pattern.compile(rejust);
		Matcher m = p.matcher(Exp);
		Matcher m1 = p1.matcher(Exp);
		while(m1.find()){
			np.ch.add(m1.group());
	     }
		while (m.find()) {
			if (!"".equals(m.group())){
				num = m.group();
				np.coef *= Integer.parseInt(num);
			}
		}
		return np;
	}
	public val Commend(String Com){
		val np1 = new val();
		String num;
		String rejust = "[a-z]";
		String rejust1 = "\\d*";
		Pattern p = Pattern.compile(rejust1);
		Pattern p1 = Pattern.compile(rejust);
		Matcher m = p.matcher(Com);
		Matcher m1 = p1.matcher(Com);
		while(m1.find()){
			np1.ch1 = m1.group();
	     }
		while (m.find()) {
			if (!"".equals(m.group())){
				num = m.group();
				np1.val = Integer.parseInt(num);
			}
		}
		return np1;
	}
	public void Simplify(ArrayList<pol>  p1,ArrayList<val>  p2){
		int i,j,k,num = 0,num1 = 0;
		for(i = 0;i < p1.size();i++){
			num = 0;
			for(j = 0;j < p1.get(i).ch.size();j++){
				for(k = 0;k < p2.size();k++){
					if(p1.get(i).ch.get(j).equals(p2.get(k).ch1)){
						p1.get(i).coef *= p2.get(k).val;
						p1.get(i).ch.set(j,"1");
					}
				}
			}
			for(j = 0;j < p1.get(i).ch.size();j++){
				if(p1.get(i).ch.get(j).equals("1")){
					num+=1;
				}
			}
			if(num < p1.get(i).ch.size()&&p1.get(i).ch.size() > 0){
				System.out.print(p1.get(i).coef);
				for(j = 0;j < p1.get(i).ch.size();j++){
					if(!p1.get(i).ch.get(j).equals("1")){
						System.out.print("*");
						System.out.print(p1.get(i).ch.get(j));
					}
				}
				if(i < p1.size()-1){
					System.out.print("+");
				}
			}
			else{
				num1 += p1.get(i).coef;
			}
		}
		if(p1.get(i-1).ch.size() > 0&&num < p1.get(i-1).ch.size()&&num1 != 0){
			System.out.print("+");
		}
		if(num1 != 0){
			System.out.print(num1);
		}
	}
	public void Derivative(ArrayList<pol>  p1,String Com){
		int num = 0,i,j,num1 = 0,num2 = 0;
		String rejust = "[a-z]";
		for(i = 0;i < p1.size();i++){
			num = 0;
			for(j = 0;j < p1.get(i).ch.size();j++){
				if(Com.equals(p1.get(i).ch.get(j))){
					num += 1;
					if(num == 1){
						p1.get(i).ch.set(j,"1");
					}
				}
			}
			p1.get(i).der = num;
		}
		for(i = 0;i < p1.size();i++){
			num = 0;
			for(j = 0;j < p1.get(i).ch.size();j++){
				if(p1.get(i).ch.get(j).matches(rejust)){
					num += 1;
				}
			}
			if(num != 0 && p1.get(i).der!=0){
				p1.get(i).coef *= p1.get(i).der;
				if(num2 == 1){
					System.out.print("+");
					num2 = 0;
				}
				System.out.print(p1.get(i).coef);
				for(j = 0;j < p1.get(i).ch.size();j++){
					if(p1.get(i).ch.get(j).matches(rejust)){
						if(num2 != 1){
							num2 = 1;
						}
						System.out.print("*");
						System.out.print(p1.get(i).ch.get(j));
					}
				}
			}
			else if(num == 0 && p1.get(i).der != 0){
				num1 = num1 + p1.get(i).coef*p1.get(i).der;
			}
		}
		if(num1 != 0){
			if(num2 == 1){
				System.out.print("+");
			}
			System.out.print(num1);
		}
	}
	public static boolean just(String Exp){
		boolean just = true;
		int num = 0;
		String regjus="^[a-z\\d*\\+\\*]+$";
		String rejust1 = "[a-z]";
		String rejust2 = "\\*";
		String rejust3 = "\\+";
		String rejust4 = "\\d*";
		if(!Exp.matches(regjus)){
			just = false;
		}
		if(Exp.length() == 1&& (Exp.equals("*")||Exp.equals("+"))){
			just = false;
		}
		if(Exp.charAt(0) == '+'||Exp.charAt(Exp.length()-1) == '+'){
			just = false;
		}
		if(Exp.charAt(0) == '*'||Exp.charAt(Exp.length()-1) == '*'){
			just = false;
		}
		if(just){
			for(int i = 0;i < Exp.length();i++){
				if(String.valueOf(Exp.charAt(i)).matches(rejust1)){
					num+=1;
				}
				else{
					num = 0;
				}
				if(num == 2){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust2)&&String.valueOf(Exp.charAt(i-1)).matches(rejust3)){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust3)&&String.valueOf(Exp.charAt(i-1)).matches(rejust2)){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust2)&&String.valueOf(Exp.charAt(i-1)).matches(rejust2)){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust3)&&String.valueOf(Exp.charAt(i-1)).matches(rejust3)){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust4)&&String.valueOf(Exp.charAt(i-1)).matches(rejust1)){
					just = false;
					break;
				}
				else if(i > 0&&String.valueOf(Exp.charAt(i)).matches(rejust1)&&String.valueOf(Exp.charAt(i-1)).matches(rejust4)){
					just = false;
					break;
				}
			}
		}
		return just;
	}
	public static boolean just1(ArrayList<pol> p1,String Com){
		boolean just1 = false;
		int i,j,num = 0;
		for(i = 0;i < p1.size();i++){
			for(j = 0;j <p1.get(i).ch.size();j++){
				if(Com.equals(p1.get(i).ch.get(j))){
					num += 1;
				}
			}
		}
		if(num != 0){
			just1 = true;
		}
		return just1;
	}
	public static void main(String[] args) {
		int i;
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		System.out.println("开始时间 "+startMili);
		Scanner sc = new Scanner(System.in);
		String Exp = sc.nextLine();
		polyval p = new polyval();
		boolean jus = p.just(Exp);
		if(!jus){
			System.out.println("Error");
		}
		else{
			System.out.println(Exp);
			String[] aa = Exp.split("\\+");
			ArrayList<pol>  p1 = new ArrayList<pol>();
			for(i = 0;i < aa.length;i++){
				p1.add(p.expression(aa[i]));
			}
			Scanner sc1 = new Scanner(System.in);
			String Com = sc1.nextLine();
			if(Com.startsWith("!")){
				Com = Com.replace("!","");
				if(Com.startsWith("simplify")){
					Com = Com.replace("simplify","");
					if(Com != ""){
						String aa1[] = Com.split("\\s");
						ArrayList<val>  p2 = new ArrayList<val>();
						for(i = 0;i < aa1.length;i++){
							p2.add(p.Commend(aa1[i]));
						}
						p.Simplify(p1,p2);
					}
					else{
						System.out.println(Exp);
					}
				}
				else if(Com.startsWith("d/d")){
					Com = Com.replace("d/d","");
					boolean just1 = p.just1(p1, Com);
					if(just1){
						p.Derivative(p1, Com);
					}
					else{
						System.out.println("Error");
					}
				}
				else{
					System.out.println("Error");
				}
			}
			else{
				System.out.println("Error");
			}
		}
	long endMili=System.currentTimeMillis();
	System.out.println("结束时间 "+endMili);
	System.out.println("执行总时间："+(endMili-startMili)+"毫秒");
	}
}
