/*  
 * Progetto ASD - Esercizio 2
 *
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
#include <ctype.h>
#include <limits.h>

#include "skiplist.h"

#define NF __FILE__
#define SIZE_BUFFER 1024 

#define C_RED   "\x1b[31m"
#define C_GREEN "\x1b[32m"
#define C_WHITE "\x1b[0m"

int compare_string(const void *a, const void *b)
{
  return strcmp(((const char *)a), ((const char *)b));
}


FILE* open_file(const char *path);
size_t convert_string_to_sizet(const char *str);
void find_errors(FILE *dictfile, FILE *textfile, size_t max_height);
void load_dictionary(struct SkipList *list, FILE *dictfile);
void test_correctme(struct SkipList *list, FILE *textfile);
void to_lower_case(char *word);
void remove_punct(char *word);



/** MAIN */
int main(int argc, char const *argv[]){
  if(argc != 4){
    printf("Please check argoment: make run dir='path/filename.txt' cor='path/filename.txt' max_height='number'\n");
    exit(EXIT_FAILURE);
  }

  FILE *dictfile = open_file(argv[1]);
  FILE *textfile = open_file(argv[2]);

  size_t max_height = convert_string_to_sizet(argv[3]);

  find_errors(dictfile, textfile, max_height);

  return 0;
}

FILE* open_file(const char *path){
  FILE *current;
  current = fopen(path, "r");
  if ( current == NULL){
    fprintf(stderr,"\n<%s>: unable to open _file.csv %s",NF,path);
    exit(EXIT_FAILURE);
  }
  return current;
}
size_t convert_string_to_sizet(const char *str) {
  char *endptr;
  unsigned long result = strtoul(str, &endptr, 10);
  
  if (*endptr != '\0') {
    printf("Incorrect max_heigt: must be a positive natural number\n");
    exit(EXIT_FAILURE);
  }
  
  return (size_t)result;
}

void find_errors(FILE *dictfile, FILE *textfile, size_t max_height){
  struct SkipList *list = NULL;
  new_skiplist(&list, max_height,compare_string);

  load_dictionary(list, dictfile);//dir
  fclose(dictfile);

  test_correctme(list, textfile);
  fclose(textfile);

  //dumpList(list);

  clear_skiplist(&list);
}

void load_dictionary(struct SkipList *list, FILE *dictfile){
  clock_t begin,end;
  char buffer[SIZE_BUFFER];

  begin = clock();
  
  while(fgets(buffer, SIZE_BUFFER, dictfile) != NULL){
    buffer[strlen(buffer)-1] = '\0';
    char* parola = strdup(buffer);
    to_lower_case(parola);
    insert_skiplist(list, parola);

  }
  
  end = clock();
  printf(C_GREEN"\t\tSucess. Time=%fs\n"C_WHITE,(double)(end-begin)/CLOCKS_PER_SEC);
}

void test_correctme(struct SkipList *list, FILE *textfile){
  clock_t begin,end;
  char buffer[50];

  begin = clock();
  while(fscanf(textfile,"%s", buffer) == 1){
    remove_punct(buffer);
    to_lower_case(buffer);
    if(search_skiplist(list, buffer) == NULL){
      printf("%s\n",buffer);
    }
  }
  end = clock();
  printf(C_GREEN"\t\tEND. Time=%fs\n"C_WHITE,(double)(end-begin)/CLOCKS_PER_SEC);
}


void to_lower_case(char *word){
  while(*word){
    if(isupper(*word)){
      *word = (char)tolower(*word);
    }
    else{
      word++;
    }
  }
}
void remove_punct(char *word){
  char *with_point = word;
  while(*with_point){
    if( ispunct( *word)){
      with_point++;
    }else{
      *word++ = *with_point++;
    }
  }
  *word = 0;
}

