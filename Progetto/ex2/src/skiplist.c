/*  
 * Progetto ASD - Esercizio 2
 *
 * Created by Badrane Torrente
*/
#include "skiplist.h"

struct SkipList {
  struct Node **heads;
  size_t max_level;
  size_t max_height;
  int (*compare)(const void*, const void*);
};

struct Node {
  struct Node **next;
  size_t size;
  void *item;
};


struct Node *createNode(void *item, size_t level);
size_t randomLevel(size_t max_height);


void new_skiplist( struct SkipList **list,
                   size_t max_height,
                   int (*compar)(const void*, const void*)){

  // Allocazione della memoria per la SkipList
  struct SkipList *newList = malloc(sizeof(struct SkipList));
  
  // Allocazione della memoria per gli array di puntatori ai nodi di ogni livello
  newList->heads = malloc(max_height * sizeof(struct Node*));
  
  // Inizializzazione degli array dei puntatori ai nodi di ogni livello con NULL
  for (size_t i = 0; i < max_height; i++) {
      newList->heads[i] = NULL;
  }
  
  // Impostazione degli altri membri della SkipList
  newList->max_level = 0; // Al momento, non ci sono nodi nella lista
  newList->max_height = max_height; 
  newList->compare = compar;
  
  // Assegna la locazione di memoria allocata a *list
  *list = newList;
}

void clear_skiplist(struct SkipList **list) {
  if (*list == NULL) {
      return; // La SkipList è già vuota, non c'è niente da liberare
  }
  
  struct SkipList *skiplist = *list;
  
  // Libera la memoria allocata per ogni nodo
  struct Node *current = skiplist->heads[0];
  while (current != NULL) {
    struct Node *next = current->next[0];
    
    // Libera la memoria allocata per l'item del nodo
    free(current->item);
    
    // Libera la memoria allocata per gli array dei puntatori ai nodi di ogni livello
    free(current->next);
    
    // Libera la memoria allocata per il nodo corrente
    free(current);
    
    current = next;
  }
  
  // Libera la memoria allocata per gli array dei puntatori ai nodi di ogni livello
  free(skiplist->heads);
  
  // Libera la memoria allocata per la SkipList stessa
  free(skiplist);
  
  *list = NULL; // Imposta il puntatore a SkipList a NULL per indicare che è stata liberata
}


void insert_skiplist(struct SkipList *list, void *item) {
  struct Node *new_node = createNode(item, randomLevel(list->max_height));
  if (new_node->size > list->max_level) {
    list->max_level = new_node->size;
  }
  
  struct Node **x = list->heads;
  //struct Node **y = list->heads;
  for (int k = (int)list->max_level-1; k >= 0; k--) {
    if (x[k] == NULL || list->compare(item, x[k]->item) < 0) {
      if (k < (int)new_node->size) {
        new_node->next[k] = x[k];
        x[k] = new_node;
        //y[k]->next = new_node;
      }
    } else {
      x = x[k]->next;
      k++;
    }
  }

  //dumpList(list);
}
struct Node *createNode(void *item, size_t level) {
  struct Node *new_node = malloc(sizeof(struct Node));
  new_node->next = malloc(level * sizeof(struct Node *));
  new_node->size = level;
  new_node->item = item;
  
  for(int i=0; i<(int)level; i++){
    new_node->next[i] = NULL;
  }

  return new_node;
}

size_t randomLevel(size_t max_height) {
  size_t lvl = 1;
  srand((unsigned int)(time(0) * rand()));
  
  // random() restituisce un valore casuale compreso tra [0...1)
  while ((double)rand() / RAND_MAX < 0.5 && lvl < max_height) {
    lvl += 1;
  }
  
  return lvl;
}


const void* search_skiplist(struct SkipList *list, void *item) {
  struct Node **x = list->heads;

  // loop invariant: x[i]->item <= item or item < first element of level i in list
  for (int i = (int)list->max_level-1; i >= 0; i--) {
    while (x[i] != NULL && list->compare(x[i]->item, item) < 0) {
      x = x[i]->next;
    }
  }

  // loop end: x[1]->item <= item or item < first element in list
  if (x[0] != NULL && list->compare(x[0]->item, item) == 0) {
    return x[0]->item;
  } else {
    return NULL; // Nessuna corrispondenza trovata
  }
}


/*-Usato per il i test 
void dumpList(struct SkipList *list){
  struct Node **nodes = list->heads;

  while (nodes[0] != NULL)
  {
    printf("[%s/%d]->", nodes[0]->item,nodes[0]->size);
    
    nodes = nodes[0]->next;
  }
  printf("nill\n\n\n");
}-*/
