/*  
 * merge_binary_insertion_sort.h
 * Progetto ASD - Esercizio 1
 *
 * Created by Badrane Torente
*/


#ifndef MERGE_BINARY_INSERTION_SORT_H
#define MERGE_BINARY_INSERTION_SORT_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>



//-base     è un puntatore al primo elemento dell'array da ordinare;
//-nitems   è il numero di elementi nell'array da ordinare;
//-size     è la dimensione in bytes di ogni elemento dell'array;
//-k        è un parametro dell'algoritmo;
//-compar   è il criterio secondo cui ordinare i dati 
//  (dati due puntatori a elementi dell'array, restituisce un numero maggiore, 
//    uguale o minore di zero se il primo argomento è rispettivamente maggiore,
//     uguale o minore del secondo).

void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));


#endif /* MERGE_BINARY_INSERTION_SORT_H */