import java.util.ArrayList;
import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		int angles = 0;
		int sides = 0;
		boolean alphaGiven = false, betaGiven = false, lambdaGiven = false, aGiven = false, bGiven = false, cGiven = false, cPair = false, aPair = false, bPair = false;
		double a = 0, b = 0, c = 0, alpha = 0 , beta = 0, lambda = 0;
		System.out.println("This program will complete non right triangles using Law of Sines/Cosines");
		
		//Angle input handling
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Alpha angle or type Solve");
		String alphaString = sc.nextLine();
		if (!alphaString.equalsIgnoreCase("Solve")) {
			angles++;
			alphaGiven = true;
			alpha = Double.parseDouble(alphaString);
		}
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter Beta angle or type Solve");
		String betaString = sc.nextLine();
		if (!betaString.equalsIgnoreCase("Solve")) {
			angles++;
			betaGiven = true;
			beta = Double.parseDouble(betaString);
		}
	
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter lambda angle or type Solve");
		String lambdaString = sc.nextLine();
		if (!lambdaString.equalsIgnoreCase("Solve")) {
			angles++;
			lambdaGiven = true;
			lambda = Double.parseDouble(lambdaString);
		}
		
		//Side input handling
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Enter side A or type Solve");
		String aString = sc.nextLine();
		if (!aString.equalsIgnoreCase("Solve")) {
			sides++;
			aGiven = true;
			a = Double.parseDouble(aString);
			if(alphaGiven) {
				aPair = true;
			}
		}
		
		Scanner sc4 = new Scanner(System.in);
		System.out.println("Enter side B or type Solve");
		String bString = sc.nextLine();
		if (!bString.equalsIgnoreCase("Solve")) {
			sides++;
			bGiven = true;
			b = Double.parseDouble(bString);
			if(betaGiven) {
				bPair = true;
			}
		}
		
		Scanner sc5 = new Scanner(System.in);
		System.out.println("Enter side C or type Solve");
		String cString = sc.nextLine();
		if (!cString.equalsIgnoreCase("Solve")) {
			sides++;
			cGiven = true;
			c = Double.parseDouble(cString);
			if(lambdaGiven) {
				cPair = true;
			}
		}
		
		//Checking for impossible
		if (angles == 3 && sides == 3) {
			System.out.println("Error: You have entered an already complete triangle!");
		}
		
		if (angles + sides < 3) {
			System.out.println("Impossible case!");
		}
		
		if (angles == 3 && sides == 0) {
			System.out.println("You have entered only angles!");
		}
		
		if(sides == 3 && angles == 0) {
			alpha = CosinesforAngle(a, b, c);
			beta =  CosinesforAngle(b, a, c);
			lambda = CosinesforAngle(c, a, b);
		}
		
		ArrayList<String> needsSolve = new ArrayList<String>();
		
		if(aGiven == false) {
			needsSolve.add("a");
		}
		if(bGiven == false) {
			needsSolve.add("b");
		}
		if(cGiven == false) {
			needsSolve.add("c");
		}
		if(alphaGiven == false) {
			needsSolve.add("alpha");
		}
		if(betaGiven == false) {
			needsSolve.add("beta");
		}
		if(lambdaGiven == false) {
			needsSolve.add("lambda");
		}
		
		for (int i = 0; i < needsSolve.size(); i++) {
			
			//Side Solver
			if(needsSolve.get(i).equals("a")) {
				if(bPair && alphaGiven) {
					a =  SinesForSide(b, beta, alpha);
				}
				if(cPair && alphaGiven) {
				a =  SinesForSide(c, lambda, alpha);
				}
			}
			if(needsSolve.get(i).equals("c")) {
				if(bPair && lambdaGiven) {
					c =  SinesForSide(b, beta, lambda);
				}
				if(aPair && lambdaGiven) {
				a =  SinesForSide(a, alpha, lambda);
				}
			}
			if(needsSolve.get(i).equals("b")) {
					if(cPair && betaGiven) {
					b =  SinesForSide(c, lambda, beta);
				}
					if(aPair && betaGiven) {
						b =  SinesForSide(a, alpha, beta);
				}
			}
			
			//Angle solvers for Law of Sines
			if(needsSolve.get(i).equals("lambda")) {
					if(bPair && cGiven) {
						lambda = SinesForAngle(b, beta, c);
				}
					if((bPair && aPair) && cGiven == false) {
						lambda = findAngle(beta, alpha);
						c =  SinesForSide(b, beta, alpha);
					}
					if(aPair && cGiven) {
						lambda = SinesForAngle(a, alpha, c);
				}
			}
			
			if(needsSolve.get(i).equals("beta")) {
				if(cPair && bGiven) {
					beta = SinesForAngle(c, lambda, b);
				}
				if((aPair && cPair) && bGiven == false) {
					lambda = findAngle(lambda, alpha);
					c =  SinesForSide(c, lambda, alpha);
				}
				if(aPair && bGiven) {
					beta = SinesForAngle(a, alpha, b);
				}
			}

			if(needsSolve.get(i).equals("alpha")) {
				if(bPair && aGiven) {
					alpha = SinesForAngle(b, beta, a);
				}
				if((bPair && cPair) && aGiven == false) {
					alpha = findAngle(beta, lambda);
					a =  SinesForSide(b, beta, alpha);
				}
				if(cPair && aGiven) {
					alpha = SinesForAngle(c, lambda, a);
				}
			}
			
	}
}
	
	//Law of sines to solve for a side given an angle
	public static double SinesForSide(double side, double angle, double angle2) {
		double answer = (side / Math.sin(angle)) * Math.sin(angle2);
		System.out.println(side + "/sin" + angle + " * sin" + angle2);
		answer = answer*Math.PI/180;//Coverts to degrees from radians
		return answer;
	}
	
	//Law of sines to solve for a angle given a Side
	public static double SinesForAngle(double side, double angle, double side2) {
		double pairSum = side / Math.sin(angle); //Calculated with what we are given
		System.out.println(side + "/sin" + angle + " = " + side2 + "/sinx");
		double solvedSum =  side2 / pairSum;
		System.out.println(pairSum + "sinx = " + side2);
		System.out.println("x = arcsin" + solvedSum);
		double arcSum = Math.asin(solvedSum);
		arcSum = arcSum*Math.PI/180;//Coverts to degrees from radians
		return arcSum;
	}
	
	//Law of cosines given 3 sides
	public static double CosinesforAngle(double a, double b, double c) {
		double leftSum = ((b*b)*(c*c)) - (a*a);
		double rightSum = 2*(b)*(c);
		System.out.println(a*a + " = " + b*b + " + " + c*c + " - 2(" + b + ")(" + c + ")cosx" );
		double totalSum = leftSum / rightSum;
		double arcSum = Math.acos(totalSum);
		arcSum = arcSum*Math.PI/180;//Coverts to degrees from radians
		return arcSum;
	}
	
	//Law of cosines given 2 sides and non pair angle for side solve
	public static double CosinesforSide(double side, double angle, double side2) {
		double sum = (side*side) + (side2*side2) - 2*(side)*(side2)*Math.cos(angle);
		double rootSum = Math.sqrt(sum);
		return rootSum;
	}
	
	//Find 180 degrees from 2 angles 
	public static double findAngle(double a, double b) {
		double answer = 180 - (a + b);
		return answer;
	}
	
}

