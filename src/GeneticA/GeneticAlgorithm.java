package GeneticA;

import GameLogic.Map;
import GameLogic.Octopus;
import java.util.Random;

public class GeneticAlgorithm {
	private final Random r = new Random();

	//Game Variables
    private final Octopus oct; 			//Current Octopus
	private final Map map;
	private final int[][] matrix;	//Game matrix

	static class Solution {
		char genotype;
		double fitness = -1;
	}

    //G.A. Variables
	private final Solution[] population;
	private int generations = 10;
	private final int size = 100;
	private final double mutationP = 0.1;

	//Solution
	private final char bestSolution;


	//Constructor
	public GeneticAlgorithm(Octopus o, Map map) { //	public GeneticAlgorithm(Octopus o, Octopus[] octopuses, int[][] matrix) {
	    oct = o;
	    this.map = map;
	    this.matrix = map.getMatrix();

        population =  new Solution[size];

        //Generate Gen 0 -- Randoms
		for (int i = 0; i < size; i++){
			population[i] = new Solution();
			population[i].genotype = (char) r.nextInt(255); //1111 1111
			fitness(population[i]);
		}

		do {
			char[] parents = selection(); 	//Selects the best parent and a random one
			newGeneration(parents); 		//Creates the population
			generations--;
		} while (generations != 0); //Do for X generations

		bestSolution = selection()[0]; //Select best one
	}


	//Fitness function
	public void fitness(Solution sol){
		int valid, EnemyHealth;

		//Get the row and col
		int row = ((sol.genotype >= 126) ? sol.genotype-126 : sol.genotype) / 42 - 1, col = (sol.genotype%42)/14 - 1;
		row += oct.getPosition()[0]; col += oct.getPosition()[1];

		//Validate row & cols are part of the matrix
		valid = (row < 0 || row >= matrix.length)? 0 : 1;
		valid *= (col < 0 || col >= matrix.length)? 0 : 1;

		if (valid == 0){
			sol.fitness = -100000; //-1000000000
			return;
		}

		//Enemy Health
		Octopus enemy = map.getOctopus(matrix[row][col]);
		EnemyHealth = (enemy != null)? enemy.getLife() : 200;
		EnemyHealth *= (enemy != null)? ((enemy.getId() == oct.getId())? -1 : 1): -1; //So it doesn't attacks itself //-1

		//Fitness
		sol.fitness = (1 - sol.genotype/126) * ( (double) 10 / EnemyHealth) + sol.genotype/126 * (-5 * matrix[row][col] + 1);
	}


	//Selects the best parent and a random one
	public char[] selection(){
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


	//Extracts k-bits from the genotype starting from pos
	public char extractBits(char genotype, int pos, int k){
		return (char) (((1 << k) - 1) & (genotype >> pos));
	}


	//Crosses the parents and gets an offspring
	public char crossover(char[] parents){
		int point = r.nextInt(4) + 2, bit;

		char a = extractBits(parents[0], 0, point); 				//Gets the lower bits of parent 0
		char offspring = extractBits(parents[1], point, 8-point); 	//Gets the upper bits of parent 1
		offspring <<= point; 											//Moves the upper bits to their designated position
		offspring |= a; 												//Adds the lower bits

		//Mutation
		if (r.nextDouble() <= mutationP){
			bit = r.nextInt(8);
			offspring &= ~(1 << bit); 									//Negates where the specified bit is
		}

		return offspring;
	}


	//Generates a new Generation
	public void newGeneration(char[] parents){
		for (int i = 0; i < size; i++){
			population[i].genotype = crossover(parents);
			fitness(population[i]);
		}
	}


	public char getBestSolution() { return bestSolution; }

}