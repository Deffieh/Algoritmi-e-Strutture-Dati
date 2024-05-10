/*  
 * Progetto ASD - Esercizio 1
 *
 * Created by Badrane Torrente
*/

#include "merge_binary_insertion_sort.h"



//-Ricerca binaria
size_t binary_search(void *base, size_t start, long end, size_t size, void *key, int (*compar)(const void*, const void*))
{
  if ( (long)start > end)
    return start;

  size_t mid = (start + (size_t)end) / 2;

  int cmp = compar(key, (char *)base + mid * size);
  if (cmp == 0)
    return mid;
  else if (cmp < 0)
    return binary_search(base, start, (long int)mid - 1, size, key, compar);
  else
    return binary_search(base, mid + 1, end, size, key, compar);
}

//-Insertion sort che usa il binari search
void binary_insertion_sort(void *base, size_t start, size_t end, size_t size, int (*compar)(const void*, const void*))
{
  for (size_t i = start + 1; i < start+end; i++) {
    void *key = malloc(size);
    if (key == NULL) {
      fprintf(stderr, "Errore nell'allocazione di memoria.\n");
      exit(EXIT_FAILURE);
    }

    memcpy(key, (char *)base + i * size, size);

    size_t j = binary_search(base, start, (long int)i, size, key, compar);

    memmove((char *)base + (j + 1) * size, 
            (char *)base + j * size,
            (i - j) * size);
    memcpy((char *)base + j * size, key, size);

    free(key);
  }
}

//-il merge del merge-sort 
void merge(char *arr, size_t l, size_t m, size_t r, size_t size, int (*compar)(const void*, const void*))
{
  char *tmp = malloc((r-l+1) * size);
  size_t i = l, j = m+1, p = 0;
  while (i <= m && j <= r) {
    if (compar(&arr[i*size], &arr[j*size]) <= 0) {
      memcpy(&tmp[p*size], &arr[i*size], size);
      i++;
    } else {
      memcpy(&tmp[p*size], &arr[j*size], size);
      j++;
    }
    p++;
  }
  while (i <= m) {
    memcpy(&tmp[p*size], &arr[i*size], size);
    i++;
    p++;
  }
  while (j <= r) {
    memcpy(&tmp[p*size], &arr[j*size], size);
    j++;
    p++;
  }
  memcpy(&arr[l*size], tmp, (r-l+1) * size);
  free(tmp);
}
//-il merge sort modificato 
void merge_binary_insertion_sort_rec(char *arr, size_t l, size_t r, size_t k, size_t size, int (*compar)(const void*, const void*))
{
  if (l >= r) return;

  if (r - l + 1 <= k) {
    binary_insertion_sort(&arr[l*size], 0, r-l+1, size, compar);
    return;
  }
  size_t mid = (l + r) / 2;
  merge_binary_insertion_sort_rec(arr, l, mid, k, size, compar);
  merge_binary_insertion_sort_rec(arr, mid+1, r, k, size, compar);

  merge( arr, l, mid, r, size, compar);
}

//-la partenza dell'algoritmo
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*))
{
  char *arr = (char*)base;
  merge_binary_insertion_sort_rec(arr, 0, nitems-1, k, size, compar);
}