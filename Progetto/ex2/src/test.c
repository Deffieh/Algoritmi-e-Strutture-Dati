/*  
 * skip_list.c
 * Progetto ASD - Esercizio 2
 *
 * testing..
 * Created by Badrane Torrente
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <time.h>
#include <math.h>
#include <limits.h>
#include "unity.h"
#include "skiplist.h"

#define MAX_HEIGHT 10

int compare_ints(const void *a, const void *b)
{
  if ((*(const int *)a) < (*(const int *)b))
    return -1;
  else 
    return ((*(const int *)a) > (*(const int *)b)) ? 1 : 0;
}

int compare_doubles(void *a, void *b)
{
  if ((*(double *)a) < (*(double *)b))
    return -1;
  else 
    return ((*(double *)a) > (*(double *)b)) ? 1:0;
}

int compare_chars(const void *a, const void *b)
{
  return strcmp(((const char *)a), ((const char *)b));
}



/*::: Testing  :::*/
static void test_Init_skipList()
{
  struct SkipList *list = NULL;
  new_skiplist(&list, MAX_HEIGHT,compare_ints);

  int *expected = NULL;

  TEST_ASSERT_EQUAL_PTR_ARRAY( expected, search_skiplist(list, (void*)4), 1);
  clear_skiplist(&list);
}
static void test_Insert_one_elem()
{
  int* input= malloc(sizeof(int));
  *input = 4;
  int* expected = input;
  struct SkipList *list = NULL;
  new_skiplist(&list, MAX_HEIGHT,compare_ints);
  insert_skiplist(list, input);
  int *actual = (int *) search_skiplist(list, (input));

  TEST_ASSERT_EQUAL_INT(*expected, *actual);
  
  clear_skiplist(&list);
}
static void test_Insert_multipl_elem()
{
  int* input= malloc(sizeof(int));
  *input = 4;
  int *expected = input;
  struct SkipList *list = NULL;
  new_skiplist(&list, MAX_HEIGHT,compare_ints);
  
  for(int i =0; i<4; i++){
    input= malloc(sizeof(int));
    *input = 4+i;
    insert_skiplist(list, input);}

  int *actual = (int *) search_skiplist(list, expected);

  TEST_ASSERT_EQUAL_INT(*expected, *actual);
  
  clear_skiplist(&list);
}


//----------------------------
static void test_Search_string(){
  char *input=strdup("prova"),
        *input1=strdup("prova2"),
        *input2=strdup("prova3"),
        *input3=strdup("prova4");
  char *expected=strdup("prova3");
  struct SkipList *list = NULL;
  new_skiplist(&list, MAX_HEIGHT,compare_chars);
  
  insert_skiplist(list, input);
  insert_skiplist(list, input1);
  insert_skiplist(list, input2);
  insert_skiplist(list, input3);
  char *actual = (char*)search_skiplist(list, input2);

  TEST_ASSERT_EQUAL_STRING(expected, actual);
  clear_skiplist(&list);
}




int main()
{
  
  UNITY_BEGIN();

  RUN_TEST(test_Init_skipList);
  RUN_TEST(test_Insert_one_elem);
  RUN_TEST(test_Insert_multipl_elem);

  RUN_TEST(test_Search_string);
  return UNITY_END();
}
