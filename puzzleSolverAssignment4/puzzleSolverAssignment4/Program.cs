using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace puzzleSolverAssignment4
{
    class Program
    {
        private static int[][] matrix;
        private static int[][] end_state;
        static void Main(string[] args)
        {
            Console.WriteLine("Assignment 4: puzzle solver");
            //Console.WriteLine("Please enter the size of the puzzle:");
            //int s = Convert.ToInt32(Console.ReadLine());

            matrix = new int[3][];
            for (int h = 0; h < 3; h++) {
                matrix[h] = new int[3];
            }
            matrix[0][0] = 1;
            matrix[0][1] = 0;
            matrix[0][2] = 2;
            matrix[1][0] = 3;
            matrix[1][1] = 4;
            matrix[1][2] = 5;
            matrix[2][0] = 6;
            matrix[2][1] = 7;
            matrix[2][2] = 8;
            

            //end state
            end_state = new int[3][];
            for (int h = 0; h < 3; h++)
            {
                end_state[h] = new int[3];
            }
            end_state[0][0] = 0;
            end_state[0][1] = 1;
            end_state[0][2] = 2;
            end_state[1][0] = 3;
            end_state[1][1] = 4;
            end_state[1][2] = 5;
            end_state[2][0] = 6;
            end_state[2][1] = 7;
            end_state[2][2] = 8;
            //print the matrix 
            // matrix_printer(matrix);
            // matrix_printer(move_right(matrix, 2,2));
            //Console.WriteLine(get0Point(matrix)[1]);


            Agent_Solver ag = new Agent_Solver(matrix,end_state);

            Console.ReadLine();
        }

        private static int[][] random_mat_gen(int s) {
            int[][] x = new int[s][];

            Random rand = new Random();

            int[] used = new int[s * s];
            int idx = 0;

            for (int i = 0; i < s; i++)
            {
                x[i] = new int[s];
                for (int j = 0; j < matrix[i].Length; j++)
                {
                    int temp = rand.Next(0, s * s);
                    Boolean exists = false;
                    for (int k = 0; k < used.Length; k++)
                    {
                        if (used[k] == temp)
                        {
                            exists = true;
                        }
                    }

                    while (exists)
                    {
                        temp = rand.Next(0, s * s);
                        Boolean temp2 = false;
                        for (int k = 0; k < used.Length; k++)
                        {
                            if (used[k] == temp)
                            {
                                exists = true;
                            }
                        }
                        exists = temp2;
                    }
                    if (!exists)
                    {
                        matrix[i][j] = temp;
                        used[idx] = temp;
                        idx++;
                    }


                }
            }
            return x;
        }
        private static void matrix_printer(int[][] mat) {
            Console.WriteLine("puzzle state: ");
           
            for (int i = 0; i < mat[0].Length; i++) {
               
                for (int j = 0; j < mat[0].Length; j++)
                {
                    //Console.WriteLine("idx"+i +" "+ j+".");
                    Console.Write(mat[i][j]);
                    Console.Write(" ");
                }
                Console.WriteLine();
                    
            } 
        }
    }
}
