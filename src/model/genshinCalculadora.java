/*genshinCalculadora*/

import java.util.Scanner;

public class genshinCalculadora {
	public static void main(String[] args) {

	int characterLevel, team, i, type, nType;
	double pdc, dc, elementalMastery, characterDamage, transMultiply, ampReactionMultiply, addMultiply, resistMultiply, totalDamage;
	Scanner input = new Scanner(System.in);

	transMultiply = 0;
	ampReactionMultiply = 0;
	addMultiply = 0;
	elementalMastery = 0;
	totalDamage = 0;

	System.out.println("Are you in a team? If so please write the team members (Remember, max team members is 4)");
	team = input.nextInt();
	double [] member = new double[team];

	for (i=0; i < (team); i++) {
		System.out.println("What type of damage does this character do? \n 0 = Transformative \n 1 = Amplificative \n 2 = Additive");
		type = input.nextInt();
		switch (type) {
		case 0:
			System.out.println("What's the character level?");
			characterLevel = input.nextInt();
			System.out.println("What's the character Elemental Mastery?");
			elementalMastery = input.nextDouble();
			System.out.println("What's type of Transformative Multiplier does your character do? \n 0 = Burn \n 1 = Superconductor \n 2 = Whirlwind \n 3 = Charged \n 4 = Crystal \n 5 = Overcharged/Flowering \n 6 = Overflowering/Superburn");
			nType = input.nextInt();
			switch (nType) {
			case 0:
				transMultiply = 0.25;
				break;
			case 1:
				transMultiply = 0.5;
				break;
			case 2:
				transMultiply = 0.6;
				break;
			case 3:
				transMultiply = 1.2;
				break;
			case 4:
				transMultiply = 1.5;
				break;
			case 5:
				transMultiply = 2;
				break;
			case 6:
				transMultiply = 3;
				break;
			}
			member[i] = transDamage(transMultiply, characterLevel, resistMultiply(), transME(elementalMastery));
			break;
		case 1:
			System.out.println("What's your character damage?");
			characterDamage = input.nextDouble();
			System.out.println("What's the character critical chance? please write in decimals");
			pdc = input.nextDouble();
			System.out.println("What's the character critical damage percentage? please write the percentage in decimals and equal or bigger than 1");
			dc = input.nextDouble();
			System.out.println("What's the character Elemental Mastery?");
			elementalMastery = input.nextDouble();
			System.out.println("What type of Amplificative damage? \n 0 = Vaporize \n 1 = Melt");
			nType = input.nextInt();
			switch (nType) {
			case 0:
				ampReactionMultiply = 1.5;
				break;
			case 1:
				ampReactionMultiply = 2;
				break;
			}
			member[i] = ampDamage(baseDamage(characterDamage, pdc, dc), ampMultiply(ampReactionMultiply, ampME(elementalMastery)));
			break;

		case 2:
			System.out.println("What's your character level?");
			characterLevel = input.nextInt();
			System.out.println("What's your character Elemental Mastery?");
			elementalMastery = input.nextDouble();
			System.out.println("What type of Additive damage? \n 0 = Intensification \n 1 = Spread ");
			nType = input.nextInt();
			switch (nType) {
			case 0:
				addMultiply = 1.15;
				break;
			case 1:
				addMultiply = 1.25;
				break;
			}
			member[i] = addDamage(addMultiply, characterLevel, addME(elementalMastery), resistMultiply());
			break;
		}

		totalDamage += member[i];
		System.out.println("Character " + (i+1) + " damage is: " + (member[i]));
	}
	System.out.println("Total damage is: " + totalDamage);
}

/** 
 * Description: This calculates the base damage of the character according to the variables
 * Pre: It requires the use of 3 variables
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param characterDamage double, it can be any number.
 * @param pdc double, it can be any number between 0 and 1
 * @param dc double, it can be any number equal or above 1
 * @return baseDamage double <info: The Base Damage of a character>
 */
	public static double baseDamage(double characterDamage, double pdc, double dc) {
		double baseDamage;
		baseDamage = characterDamage * (1 + pdc + dc);
		return baseDamage;
	}

/** 
 * Description: This calculates a random number between 0.5 and 2
 * Pre: It doesn't require any variabñe
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @return resistMultiply double <info: The resistance, which is a random number between 0.5 and 2>
 */
	public static double resistMultiply() {
		double resistMultiply;
		resistMultiply = Math.random() * (2 - 0.5 + 1) + 0.5;
		return resistMultiply;
	}

/** 
 * Description: This calculates the damage if the character is using transformative damage
 * Pre: It requires the use of 5 variables
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param elementalMastery double, it can be any number and is for all ME variables.
 * @param transME double, is the result of its own formula
 * @param transMultiply double, between the types of transformative multipliers
 * @param resistMultiply double, the random number generated for resistMultiply
 * @param characterLevel int, the level of the character given by the user.
 * @return transDamage double <info: The result for the equation according to the variables>.
 */
	public static double transME(double elementalMastery) {
		double transME;
		transME = 16 * (elementalMastery / (elementalMastery + 2000));
		return transME;
	}
	public static double transDamage(double transMultiply, int characterLevel, double resistMultiply, double transME) {
		double transDamage;
		transDamage = transMultiply * characterLevel * (1 + transME) * resistMultiply;
		return transDamage;
	}

/** 
 * Description: This calculates the damage if the character is using amplificative damage
 * Pre: It requires the use of 5 variables
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param elementalMastery double, it can be any number and is for all ME variables.
 * @param ampME double, is the result of its own formula
 * @param ampReactionMultiply double, between the types of amplificative reaction multipliers
 * @param ampMultiply double, the solution of its own formula with ampME and ampReactionMultiply
 * @param baseDamage double, the solution of its own formula.
 * @return ampDamage double <info: The result for the equation according to the variables>.
 */
/* Amplification Damage */
	public static double ampME(double elementalMastery) {
		double ampME;
		ampME = 2.78 * (elementalMastery / (elementalMastery + 1400));
		return ampME;
	}
	public static double ampMultiply(double ampReactionMultiply, double ampME) {
		double ampMultiply;
		ampMultiply = ampReactionMultiply * (1 + ampME);
		return ampMultiply;
	}
	public static double ampDamage(double baseDamage, double ampMultiply) {
		double ampDamage;
		ampDamage = (baseDamage * ampMultiply);
		return ampDamage;
	}

/** 
 * Description: This calculates the damage if the character is using additive damage
 * Pre: It requires the use of 5 variables
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param elementalMastery double, it can be any number and is for addME.
 * @param addME double, is the result of its own formula
 * @param addMultiply double, between the types of additive multipliers
 * @param resistMultiply double, the random number generated for resistMultiply
 * @param characterLevel int, the level of the character given by the user
 * @return addDamage double <info: The result for the equation according to the variables>.
 */
/* Additive Damage */
	public static double addME(double elementalMastery) {
		double addME;
		addME = (5 * elementalMastery) / (elementalMastery + 1200);
		return addME;
	}
	public static double addDamage(double addMultiply, int characterLevel, double addME, double resistMultiply) {
		double addDamage;
		addDamage = addMultiply * characterLevel * (1 + addME) * resistMultiply;
		return addDamage;
	}
}