using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace puzzleSolverAssignment4
{
    class Agent_Solver
    {
        
        private int[][] matrix;
        private int[][] end_state;

        public Agent_Solver(int[][] problem, int[][] goal)
        {
            matrix = problem;
            end_state = goal;

            int[] temp = get0Point(matrix);
            Console.WriteLine(solver(matrix));
        }

        private Boolean solver(int[][] pgy)
        {
            bool tobereturned = false;




            int[][] pio = new int[pgy.Length][];

            for (int iii = 0; iii < pgy.Length; iii++)
            {
                pio[iii] = new int[pgy.Length];
                for (int jjj = 0; jjj < pgy.Length; jjj++)
                {
                    pio[iii][jjj] = pgy[iii][jjj];
                }
            }



            matrix_printer(pio);
            if (isEqualx(pio, end_state))
            {
                tobereturned = true;
            }
            else
            {
                
                if (!isEqualx(move_right(pio), pio))
                {
                    Console.WriteLine("right");
                    tobereturned = solver(move_right(pio));
                }
                
               
                if (!isEqualx(move_left(pio), pio))
                {
                    
                    tobereturned = solver(move_left(pio));
                }
                  
            }
            return tobereturned;
        }
        private Boolean isEqualx(int[][] a, int[][] b)
        {
            Boolean t = true;
            for (int i = 0; i < a[0].Length; i++)
            {
                for (int j = 0; j < a[0].Length; j++)
                {
                    if (a[i][j] != b[i][j])
                    {
                        t = false;
                        break;
                    }
                }
            }
            return t;
        }
        private int[][] move_up(int[][] matq)
        {
            int[] tempxx = get0Point(matq);
            int i = tempxx[0];
            int j = tempxx[1];

            if (i != 0)
            {
                int temp = matq[i][j];
                matq[i][j] = matq[i - 1][j];
                matq[i - 1][j] = temp;

            }
            return matq;
        }

        private int[][] move_down(int[][] mato)
        {
            int[] tempxx = get0Point(mato);
            int i = tempxx[0];
            int j = tempxx[1];

            if (i < mato[0].Length - 1)
            {
                int temp = mato[i][j];
                mato[i][j] = mato[i + 1][j];
                mato[i + 1][j] = temp;


            }
            return mato;
        }

        private int[][] move_left(int[][] matz)
        {
            int[][] po = new int[matz.Length][];

            for (int iii = 0; iii < matz.Length; iii++)
            {
                po[iii] = new int[matz.Length];
                for (int jjj = 0; jjj < matz.Length; jjj++)
                {
                    po[iii][jjj] = matz[iii][jjj];
                }
            }
            int[] tempxx = get0Point(po);
            int i = tempxx[0];
            int j = tempxx[1];

            if (j != 0)
            {
                int temp = po[i][j];
                po[i][j] = po[i][j - 1];
                po[i][j - 1] = temp;


            }
           return po;
        }
        private int[][] move_right(int[][] matz)
        {
            int[][] po = new int[matz.Length][];

            for (int iii = 0; iii < matz.Length; iii++)
            {
                po[iii] = new int[matz.Length];
                for (int jjj = 0; jjj < matz.Length; jjj++)
                {
                    po[iii][jjj] = matz[iii][jjj];
                }
            }
            int[] tempxx = get0Point(po);
            int i = tempxx[0];
            int j = tempxx[1];

            if (j < po[0].Length - 1)
            {
                int temp = po[i][j];
                po[i][j] = po[i][j + 1];
                po[i][j + 1] = temp;

            }
            return po;
        }
        private int[] get0Point(int[][] mat)
        {
            int[] g = new int[2];
            for (int i = 0; i < mat.Length; i++)
            {
                for (int j = 0; j < mat.Length; j++)
                {
                    if (mat[i][j] == 0)
                    {
                        g[0] = i;
                        g[1] = j;
                    }
                }
            }
            return g;
        }

        private void matrix_printer(int[][] mat)
        {
            Console.WriteLine("puzzle state: ");

            for (int i = 0; i < mat[0].Length; i++)
            {

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
