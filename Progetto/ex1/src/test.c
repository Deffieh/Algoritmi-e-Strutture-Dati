/*  
 * Progetto ASD - Esercizio 1
 *
 * testing..
 * Created by Badrane Torrente
*/

#include <string.h>


#define UNITY_INCLUDE_DOUBLE 
#include "unity.h"
#include "merge_binary_insertion_sort.h"


int compare_ints(const void *a, const void *b)
{
  if ((*(const int *)a) < (*(const int *)b))
    return -1;
  else 
    return ((*(const int *)a) > (*(const int *)b)) ? 1 : 0;
}
int compare_doubles(const void *a, const void *b)
{
  if ((*(const double *)a) < (*(const double *)b))
    return -1;
  else 
    return ((*(const double *)a) > (*(const double *)b)) ? 1:0;
}
int compare_strings(const void *a, const void *b) {
    const char *str1 = *(const char **)a;
    const char *str2 = *(const char **)b;
    return strcmp(str1, str2);
}




/*::: Testing Integer :::*/
static void test_one_elem_int()
{
  int input[]    ={7};
  int expected[] ={7};
  merge_binary_insertion_sort(input, 1, sizeof(int), 5, compare_ints);
  TEST_ASSERT_EQUAL_INT_ARRAY( expected, input, 1);
}
static void test_three_elem_int()
{
  int input[]    ={ 7, 7, 7};
  int expected[] ={ 7, 7, 7};
  merge_binary_insertion_sort(input, 3, sizeof(int), 5, compare_ints);
  TEST_ASSERT_EQUAL_INT_ARRAY( expected, input, 3);
}
static void test_three_elem_int_2()
{
  int input[]    ={ 9, 20, 7};
  int expected[] ={ 7, 9, 20};
  merge_binary_insertion_sort(input, 3, sizeof(int), 5, compare_ints);
  TEST_ASSERT_EQUAL_INT_ARRAY( expected, input, 3);
}

/*::: Testing Double :::*/
static void test_one_elem_double()
{
  double input[]    ={7.9};
  double expected[] ={7.9};
  merge_binary_insertion_sort(input, 1, sizeof(double), 5, compare_doubles);
  TEST_ASSERT_EQUAL_DOUBLE_ARRAY( expected, input, 1);
}
static void test_three_elem_double()
{
  double input[]    ={ 9.9, 20.9, 7.9};
  double expected[] ={ 7.9, 9.9, 20.9};
  merge_binary_insertion_sort(input, 3, sizeof(double), 5, compare_doubles);

  for(int i = 0; i<3; i++){
    printf("%f\n",input[i]);
    printf("%f\n",expected[i]);
  }
  TEST_ASSERT_EQUAL_DOUBLE_ARRAY( expected, input, 3);
}

/*::: Testing String :::*/
static void test_one_elem_char()
{
  char *input[]    ={"testing"};
  char *expected[] ={"testing"};
  merge_binary_insertion_sort(input, 1, sizeof(char)*10, 5, compare_strings);
  TEST_ASSERT_EQUAL_STRING_ARRAY( expected, input, 1);
}
static void test_three_elem_char()
{
    char *input[]    = { "ciao", "no", "cavallo" };
    char *expected[] = { "cavallo", "ciao", "no" };

    merge_binary_insertion_sort(input, 3, sizeof(char *), 5, compare_strings);

    // Verifica se l'array è stato ordinato correttamente
    for (int i = 0; i < 3; i++) {
        printf("%s\n", input[i]);
    }
  TEST_ASSERT_EQUAL_STRING_ARRAY( expected, input, 3);
}


int main(){
  
  UNITY_BEGIN();

  RUN_TEST(test_one_elem_int);
  RUN_TEST(test_three_elem_int);
  RUN_TEST(test_three_elem_int_2);

  RUN_TEST(test_one_elem_double);
  RUN_TEST(test_three_elem_double);
  
  RUN_TEST(test_one_elem_char);
  RUN_TEST(test_three_elem_char);

  return UNITY_END();
}
