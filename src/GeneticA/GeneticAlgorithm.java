package GeneticA;

import GameLogic.Octopus;
import java.util.Random;

public class GeneticAlgorithm {
	private final Random r = new Random();
    private Octopus oct;
    private Octopus[] Octopuses;

	private int generations = 10;
	private final int size = 100;
	private double mutationP = 0.1;

	private char bestSolution;

	private int[][] matrix;

	class Solution{
		char genotype;
		double fitness = -1;
	}

	private Solution[] population;

	public GeneticAlgorithm(Octopus o, Octopus[] octopuses, int[][] matrix) {
	    oct = o; this.Octopuses = octopuses;
	    this.matrix = matrix;
        population =  new Solution[size];

        //Generate Gen 0 -- Randoms
		for (int i = 0; i < size; i++){
			population[i].genotype = (char) r.nextInt(255); //1111 1111
			fitness(population[i]);
		}

		do {
			char[] parents = selection(); //Selects the best parent and a random one
			generatePopulation(parents); //Creates the population

			generations--;
		} while (generations != 0); //Do for X generations

		bestSolution = selection()[0]; //Select best one
	}

	//Get Octopus by it's id
	public Octopus getOctopus(int id){
		for (Octopus o : Octopuses){
			if (o.getId() == id)
				return o;
		} return null;
	}

	//Fitness function
	public double fitness(Solution sol){
		int valid, EnemyHealth;

		//Get the row and col
		int row = ((sol.genotype >= 126) ? sol.genotype-126 : sol.genotype) / 42 - 1, col = (sol.genotype%42)/14 - 1;
		row += oct.getPosition()[0]; col += oct.getPosition()[1];

		//Validate row & cols are part of the matrix
		valid = (row < -1 || row >= matrix.length)? 0 : 1;
		valid *= (col < -1 || col >= matrix.length)? 0 : 1;

		//Enemy Health
		Octopus enemy = getOctopus(matrix[row][col]);
		EnemyHealth = (enemy != null)? enemy.getLife() : 100000;
		EnemyHealth *= (enemy != null)? ((enemy.getId() == oct.getId())? -1 : 1): -1; //So it doesn't attacks itself

		//Fitness
		sol.fitness =  (double) ((1 - sol.genotype/126) * ( (double) 10 / EnemyHealth) + sol.genotype/126 * (-5 * matrix[row][col] + 1));
		sol.fitness *= valid;

        return sol.fitness;
	}

	public char[] selection(){
		//Select the best parent and then a random one
		char[] parents = new char[2];
		double bestF = population[0].fitness;

		for (Solution sol: population){
			if (sol.fitness >= bestF){
				parents[0] = sol.genotype;
				bestF = sol.fitness;
			}
		}

		parents[1] = population[r.nextInt(size)].genotype; //Random second parent
		return parents;
	}

	public char extractBits(char genotype, int pos, int k){ //Extracts k-bits from the genotype starting from pos
		return (char) (((1 << k) - 1) & (genotype >> pos));
	}

	public char crossover(char[] parents){
		int point = r.nextInt(4) + 2, bit;

		char a = extractBits(parents[0], 0, point); //Gets the lower bits of parent 0
		char offspring = extractBits(parents[1], point, 8-point); //Gets the upper bits of parent 1
		offspring <<= point; 	//Moves the upper bits to their designated position
		offspring |= a; 		//Adds the lower bits

		//Mutation
		if (r.nextDouble() <= mutationP){
			bit = r.nextInt(8);
			offspring &= ~(1 << bit); //Negates where the specified bit is
		}

		return offspring;
	}

	public void generatePopulation(char[] parents){ //char[] fathers
		for (int i = 0; i < size; i++){
			population[i].genotype = crossover(parents);
			fitness(population[i]);
		}
	}

}

// genetic representation of a solution

// function to generate new solutions (generations)

// fitness function

// Att
// -> 0 no hit or doesn't exists
// -> 1 (hit) + X (enemy w/ less health)

//Mov
// -> 0 used space or doesn't exists
// -> 1 free

/*
Validate valid spaces --> o.row + row, o.col + col -> -1000
EnemyHealth	--> enemy.getHealth || -1 (no enemy on that space or octopus own id there)
Fitness	--> (1 - Num / 126) * (10 / EnemyHealth) + (Num / 126) * (-5 * matrix[o.row + row][o.col + col] + 1)
 */

// selection function

// crossover function

// mutation function