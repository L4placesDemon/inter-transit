import os
import sys

if __name__ == "__main__":
    if len(sys.argv) > 1:
        path = sys.argv[1]

        for i in range(1, 11):
            os.system(f"mkdir {path}Tema-{i}")

            with open(f"{path}Tema-{i}/descripcion.txt", "w") as file:
                file.write(f"Descripcion Tema {i}\n")

            for j in range(1, 11):
                with open(f"{path}Tema-{i}/Tip {j}.txt", "w") as file:
                    file.write(f"Contenido Tip {j}\n")
