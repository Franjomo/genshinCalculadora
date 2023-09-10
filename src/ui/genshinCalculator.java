package ui;

import java.util.Scanner;

public class genshinCalculator{
    static Scanner input;

    public static void main(String[] args){
        input = new Scanner(System.in);
        menu();
    }
        
    public static void menu(){
        boolean flag = false;
        int option, type, position=0;
        double totalDamage = 0, damage;
        double[] lastDamages = new double[10];

        while(!flag){
            if(position==10){
                position=0;
            }

            System.out.println("Options: \n 0 = Calculate single character damage \n 1 = Calculate team damage \n 2 = See last 10 damages \n 3 = End");
            option = input.nextInt();

            switch(option){
                case 0:
                    System.out.println("What type of damage does your character do? \n 0 = Transformative \n 1 = Amplificative \n 2 = Additive");
                    type = input.nextInt();
                    switch(type){
                        case 0:
                            totalDamage=transformativeProcess();
                            System.out.println("----------\n The damage of the character is: " + totalDamage + "\n ----------");
                            lastDamages[position]=totalDamage;
                            position++;
                        break;

                        case 1:
                            totalDamage=amplificativeProcess();
                            System.out.println("----------\n The damage of the character is: " + totalDamage + "\n ----------");
                            lastDamages[position]=totalDamage;
                            position++;
                        break;

                        case 2:
                            totalDamage=additiveProcess();
                            System.out.println("----------\n The damage of the character is: " + totalDamage + "\n ----------");
                            lastDamages[position]=totalDamage;
                            position++;
                        break;
                    }
                break;

                case 1:
                    for(int i = 0; i<4; i++){
                        System.out.println("What type of damage does character " + (i+1) + " do? \n 0 = Transformative \n 1 = Amplificative \n 2 = Additive");
                        type = input.nextInt();
                        switch(type){
                            case 0:
                                damage=transformativeProcess();
                                System.out.println("----------\n The damage of character " + (i+1) + " is: " + damage + "\n ----------");
                                totalDamage+=damage;
                            break;

                            case 1:
                                damage=amplificativeProcess();
                                System.out.println("----------\n The damage of character " + (i+1) + " is: " + damage + "\n ----------");
                                totalDamage+=damage;
                            break;

                            case 2:
                                damage=additiveProcess();
                                System.out.println("----------\n The damage of character " + (i+1) + " is: " + damage + "\n ----------");
                                totalDamage+=damage;
                            break;
                        }
                    }
                    System.out.println("Total team damage is: " + totalDamage);
                    lastDamages[position]=totalDamage;
                    position++;
                break;

                case 2:
                    System.out.println("The last 10 damages are:");
                    for (int i = 0; i<10; i++){
                        System.out.println("----------\n " + (i+1) + ": " + lastDamages[i]);
                    }
                break;

                case 3:
                    flag = true;
                    System.exit(0);
                break;

                default:
                    System.out.print("Please enter a valid option");
                break;
            }
        }

    }

/** 
 * Description: This asks the user about everything required to calculate the Transformative Damage
 * Pre: It requires the use of 4 variables.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param transMultiply double, a number that the user chooses.
 * @param characterLevel int, The level of the character
 * @param resistMultiply double, a random number between 0.5 and 2
 * @param transME double, an equation that is required to get the Transformative Damage
 * @return transDamage double <info: The Transformative Damage of a character>
 */
    public static double transformativeProcess(){
        int characterLevel, nType;
        double elementalMastery;

        System.out.println("What's the character level?");
		characterLevel = input.nextInt();
		System.out.println("What's the character Elemental Mastery?");
		elementalMastery = input.nextDouble();
		System.out.println("What's type of Transformative Multiplier does your character do? \n 0 = Burn \n 1 = Superconductor \n 2 = Whirlwind \n 3 = Charged \n 4 = Crystal \n 5 = Overcharged/Flowering \n 6 = Overflowering/Superburn");
		nType = input.nextInt();

        return transDamage(transMultiply(nType), characterLevel, resistMultiply(), transME(elementalMastery));
    }

/** 
 * Description: This asks the user about everything required to calculate the Amplificative Damage
 * Pre: It requires the use of 4 variables.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param ampReactionMultiply double, a number that the user chooses, is for ampMultiply.
 * @param ampMultiply double, is required to get the Amplificative Damage
 * @param baseDamage double, the base damage of the character.
 * @param ampME double, an equation that is required to get the Amplificative Damage
 * @return ampDamage double <info: The Amplificative Damage of a character>
 */
    public static double amplificativeProcess(){
        double characterDamage, pdc, dc, elementalMastery;
        int nType;

        System.out.println("What's your character damage?");
		characterDamage = input.nextDouble();
		System.out.println("What's the character critical chance? please write in decimals between 0 and 1");
		pdc = input.nextDouble();
		System.out.println("What's the character critical damage percentage? please write in decimals between 0 and 1");
		dc = input.nextDouble();
		System.out.println("What's the character Elemental Mastery?");
		elementalMastery = input.nextDouble();
		System.out.println("What type of Amplificative damage? \n 0 = Vaporize \n 1 = Melt");
		nType = input.nextInt();

        return ampDamage(baseDamage(characterDamage, pdc, dc), ampMultiply(ampReactionMultiply(nType), ampME(elementalMastery)));
    }

/** 
 * Description: This asks the user about everything required to calculate the Additive Damage
 * Pre: It requires the use of 4 variables.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param characterLevel int, the level of the character.
 * @param ampMultiply double, is required to get the Amplificative Damage
 * @param resistMultiply double, a random number between 0.5 and 2.
 * @param addME double, an equation that is required to get the Amplificative Damage
 * @return addDamage double <info: The Additive Damage of a character>
 */
    public static double additiveProcess(){
        int characterLevel, nType;
        double elementalMastery;

        System.out.println("What's your character level?");
		characterLevel = input.nextInt();
		System.out.println("What's your character Elemental Mastery?");
		elementalMastery = input.nextDouble();
		System.out.println("What type of Additive damage? \n 0 = Intensification \n 1 = Spread ");
		nType = input.nextInt();

        return addDamage(addMultiply(nType), characterLevel, addME(elementalMastery), resistMultiply());
    }

/** 
 * Description: This gives the choosed number for the Transformative Damage
 * Pre: It requires 1 variable.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param nType int, an int number between 0 and 6 to choose.
 * @return transMultiply double <info: The multiply to calculate Transformative Damage>
 */
    public static double transMultiply(int nType){
        double transMultiply = 0;
        switch(nType){
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
        return transMultiply;
    }

/** 
 * Description: This gives the choosed number for the Amplification Damage
 * Pre: It requires 1 variable.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param nType int, an int number between 0 and 1 to choose.
 * @return ampReactionMultiply double <info: The multiply to calculate Amplificative Multiply>
 */
    public static double ampReactionMultiply(int nType){
        double ampReactionMultiply=0;
        switch(nType){
            case 0:
                ampReactionMultiply=1.5;
            break;
            
            case 1:
                ampReactionMultiply=2;
            break;
        }
        return ampReactionMultiply;
    }

/** 
 * Description: This gives the choosed number for the Additive Damage
 * Pre: It requires 1 variable.
 * Pos: It is used for other calculations in the code, but doesn't affect global
 * @param nType int, an int number between 0 and 1 to choose.
 * @return addMultiply double <info: The multiply to calculate Additive Damage>
 */
    public static double addMultiply(int nType){
        double addMultiply=0;
        switch(nType){
            case 0:
                addMultiply=1.15;
            break;
               
            case 1:
                addMultiply=1.25;
            break;
        }
        return addMultiply;
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
 * Pre: It doesn't require any variable
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
    public static double transME(double elementalMastery){
        double transME;
        transME = 16 * (elementalMastery / (elementalMastery + 2000));
        return transME;
    }
    public static double transDamage(double transMultiply, int characterLevel, double resistMultiply, double transME){
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