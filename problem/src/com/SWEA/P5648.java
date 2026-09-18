package com.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P5648 {


    final static float[][] moving = new float[][]{{0.5f, 0}, {-0.5f, 0}, {0, -0.5f}, {0, 0.5f}};

    public static class Atom {
        float row;
        float col;
        int power;
        int move;
        boolean end = false;

        public Atom(float row, float col, int power, int move) {
            this.row = row;
            this.col = col;
            this.power = power;
            this.move = move;
        }

        public boolean move() {
            float newR = row + moving[move][0];
            float newC = col + moving[move][1];

            if (newR < -1000 || newR > 1000 || newC < -1000 || newC > 1000) return false;

            row = newR;
            col = newC;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());


            HashMap<Integer, ArrayList<Atom>> tree = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int move = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());


                Atom atom = new Atom(r, c, k, move);


                if (!tree.containsKey(move)) {
                    tree.put(move, new ArrayList<>());
                }
                tree.get(move).add(atom);
            }

            int totalPower = 0;
            for (int co = 0; co < 2000; co++) {
                // 움직이기
                for (int i = 0; i < 4; i++) {
                    if (!tree.containsKey(i)) continue;
                    ArrayList<Atom> atoms = tree.get(i);

                    for (int j = atoms.size() - 1; j >= 0; j--) {
                        Atom a = atoms.get(j);

                        if (!a.move() || a.end) {
                            atoms.remove(j);
                        }
                    }

                }

                for (int i = 0; i < 4; i++) {
                    if (!tree.containsKey(i)) continue;
                    ArrayList<Atom> atomsM = tree.get(i);
                    int atomSize = atomsM.size();

                    for (int j = 0; j < atomSize; j++) {
                        for (int in = i + 1; in < 4; in++) {
                            if (!tree.containsKey(in)) continue;
                            ArrayList<Atom> atomsS = tree.get(in);
                            int slaveSize = atomsS.size();
                            for (int index = 0; index < slaveSize; index++) {
                                Atom master = atomsM.get(j);
                                Atom slave = atomsS.get(index);
                                if (master.row == slave.row && master.col == slave.col) {
                                    if (!master.end) {
                                        totalPower += master.power;
                                        master.end = true;
                                    }
                                    if (!slave.end) {
                                        totalPower += slave.power;
                                        slave.end = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + totalPower);
        }
    }
}
