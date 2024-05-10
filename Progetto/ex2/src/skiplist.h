/*  
 * Progetto ASD - Esercizio 2
 *
 * Created by Badrane Torrente
*/
#ifndef SKIPLIST_H
#define SKIPLIST_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <time.h>
#include <math.h>
#include <limits.h>
#include <errno.h>

struct SkipList;

void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*));
void clear_skiplist(struct SkipList **list);
void insert_skiplist(struct SkipList *list, void *item);
const void* search_skiplist(struct SkipList *list, void *item); 


//void dumpList(struct SkipList *list);

#endif