# Binary Search (Java)

## Descripción

Búsqueda binaria es un algoritmo para encontrar un elemento en un arreglo **ordenado** dividiendo repetidamente el espacio de búsqueda a la mitad.

Requisitos:

- El arreglo debe estar ordenado (ascendente en esta implementación).

Complejidad:

- Tiempo: O(log n)
- Espacio: O(1) (versión iterativa)

## Implementación incluida

- `Main.java` contiene una implementación iterativa de búsqueda binaria y una rutina `main` con pruebas.

## Uso

1. Compilar:
   javac Main.java
2. Ejecutar:
   java Main

## Prueba de escritorio (ejemplo)

Arreglo: [1, 3, 5, 7, 9, 11, 15]
Buscar 9:

- low=0, high=6 → mid = 3 → a[3]=7 < 9 → low = mid+1 = 4
- low=4, high=6 → mid = 5 → a[5]=11 > 9 → high = mid-1 = 4
- low=4, high=4 → mid = 4 → a[4]=9 → encontrado en índice 4

Buscar 2:

- low=0, high=6 → mid=3 → a[3]=7 > 2 → high=2
- low=0, high=2 → mid=1 → a[1]=3 > 2 → high=0
- low=0, high=0 → mid=0 → a[0]=1 < 2 → low=1 → low>high → no encontrado

## Resultado ejemplo (salida del programa)

Encontrado 9 en índice 4  
Encontrado 1 en índice 0  
Encontrado 15 en índice 6  
2 no encontrado en el arreglo  
Encontrado 11 en índice 5

## Notas

- Para buscar en estructuras más complejas (listas enlazadas) se necesitan otras técnicas.
- Si quieres, puedo añadir la versión recursiva y pruebas unitarias (JUnit).
