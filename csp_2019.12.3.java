import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String num_s = in.nextLine();
		int num = Integer.parseInt(num_s);
		String[] exquations = new String[num];
		for (int i = 0; i < num; i++) {
			exquations[i] = in.nextLine();
		}
		for (int i = 0; i < num; i++) {
			HashMap<String, Integer> expr_1 = new HashMap<String, Integer>();
			HashMap<String, Integer> expr_2 = new HashMap<String, Integer>();
			expr_1.putAll(statistics_expr(exquations[i].split("=")[0]));// 将左表达式转化为元素和元素数量的哈希表
			expr_2.putAll(statistics_expr(exquations[i].split("=")[1]));// 将右表达式转化为元素和元素数量的哈希表
			if (expr_1.equals(expr_2)) // 两边的元素种类与数量相等即配平
				System.out.println('Y');
			else
				System.out.println('N');
		}
		in.close();
	}

	private static HashMap<String, Integer> statistics_expr(String expr) {// 表达式转换为元素和元素数量的哈希表
		// TODO Auto-generated method stub
		ArrayList<String> part = new ArrayList<String>();
		for (String string : expr.split("\\+")) {// 表达式分割为化学式
			part.add(string);
		}
		HashMap<String, Integer> total_element = new HashMap<String, Integer>();
		for (int i = 0; i < part.size(); i++) {// 每个部分分为系数与化学式通过part_elemant处理
			StringBuilder coef = new StringBuilder(); // 系数
			StringBuilder formula = new StringBuilder();
			formula.append(part.get(i));
			char first_latter = formula.charAt(0);
			while (Character.isDigit(first_latter)) {// 读出系数存入coef
				coef.append(first_latter);
				formula.deleteCharAt(0);
				if (formula.length() == 0)
					first_latter = '\n';
				else
					first_latter = formula.charAt(0);
			}
			String coef_string = coef.toString();
			int coef_int = 1;// 默认为1，当有系数时更改为系数
			if (coef_string.length() != 0) {
				coef_int = Integer.parseInt(coef_string);
			}
			HashMap<String, Integer> part_elemant = new HashMap<String, Integer>();
			part_elemant.putAll(partition_part(formula, coef_int));// 获得该化学式的元素与数量，并保证传入的化学式没有系数
			for (HashMap.Entry<String, Integer> entry : part_elemant.entrySet()) {// 并入总哈希表
				if (total_element.containsKey(entry.getKey())) {// 总哈希表有该部分的元素
					String key = entry.getKey();
					total_element.replace(key, total_element.get(key) + entry.getValue());
				} else {
					total_element.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return total_element;
	}

	private static HashMap<String, Integer> partition_part(StringBuilder formula, int coef) {// 获得该化学式的元素与数量
		// TODO Auto-generated method stub
		HashMap<String, Integer> total_element = new HashMap<String, Integer>();
		char first_letter = formula.charAt(0);
		while (formula.length() != 0) {
			if (Character.isUpperCase(first_letter)) {// 元素
				StringBuilder element = new StringBuilder();
				StringBuilder coef_new = new StringBuilder();// 元素的系数
				element.append(first_letter);
				formula.deleteCharAt(0);
				if (formula.length() == 0)
					first_letter = '\n';
				else
					first_letter = formula.charAt(0);
				if (Character.isLowerCase(first_letter)) {// 两个字符的元素
					element.append(first_letter);
					formula.deleteCharAt(0);
					if (formula.length() == 0)
						first_letter = '\n';
					else
						first_letter = formula.charAt(0);
				}
				String eleString = element.toString();
				while (Character.isDigit(first_letter)) {// 读出系数存入coef
					coef_new.append(formula.charAt(0));
					formula.deleteCharAt(0);
					if (formula.length() == 0)
						first_letter = '\n';
					else
						first_letter = formula.charAt(0);
				}
				String coef_string = coef_new.toString();
				int coef_int = coef;// 默认为coef，当有系数时更改为系数
				if (coef_string.length() != 0) {
					coef_int = coef * Integer.parseInt(coef_string);
				}
				if (total_element.containsKey(eleString)) {
					total_element.replace(eleString, total_element.get(eleString) + coef_int);
				} else {
					total_element.put(eleString, coef_int);
				}
			} else if (first_letter == '(') {// 是化学式
				formula.deleteCharAt(0);
				int num_bracket = 1;// ‘（’数量
				int index = 0;// 寻找位置
				StringBuilder expression = new StringBuilder();
				StringBuilder coef_new = new StringBuilder();// 子化学式的系数
				do {
					if (formula.charAt(index) == '(')
						num_bracket++;
					if (formula.charAt(index) == ')')
						num_bracket--;
					expression.append(formula.charAt(index));
					formula.deleteCharAt(0);// 吃掉一位
				} while (num_bracket != 0);
				expression.deleteCharAt(expression.length() - 1);// 最后一位是‘）’删除
				if (formula.length() == 0)
					first_letter = '\n';
				else
					first_letter = formula.charAt(0);
				while (Character.isDigit(first_letter)) {// 读出系数存入coef
					coef_new.append(formula.charAt(0));
					formula.deleteCharAt(0);
					if (formula.length() == 0)
						first_letter = '\n';
					else
						first_letter = formula.charAt(0);
				}
				String coef_string = coef_new.toString();
				int coef_int = coef;// 默认为coef，当有系数时更改为系数
				if (coef_string.length() != 0) {
					coef_int = coef * Integer.parseInt(coef_string);
				}
				HashMap<String, Integer> map_new = partition_part(expression, coef_int);// 递归的把字化学式的元素求出来
				for (HashMap.Entry<String, Integer> entry : map_new.entrySet()) {// 并入总哈希表
					if (total_element.containsKey(entry.getKey())) {// 总哈希表有该部分的元素
						String key = entry.getKey();
						total_element.replace(key, total_element.get(key) + entry.getValue());
					} else {
						total_element.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		return total_element;
	}
}
/*
11
H2+O2=H2O  
2H2+O2=2H2O
H2+Cl2=2NaCl
H2+Cl2=2HCl
CH4+2O2=CO2+2H2O
CaCl2+2AgNO3=Ca(NO3)2+2AgCl
3Ba(OH)2+2H2PO4=6H2O+Ba2(PO4)2
3Ba(OH)2+2H2PO4=Ba2(PO4)2+6H2O
4Zn+10HNO3=4Zn(NO3)2+NH4NO3+3H2O
4Na(Au(CN)2)+4NaOH=4Au+8NaCN+2H2O+O2
Cu+As=Cs+Au
N Y N Y Y N N Y Y N
 * 
 */
