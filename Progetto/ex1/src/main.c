/*  
 * Progetto ASD - Esercizio 1
 *
 * Created by Badrane Torrente
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "merge_binary_insertion_sort.h"

#define NF __FILE__
#define SIZE_BUFFER 1024
#define SIZE_FIELDS 20000000

#define C_RED   "\x1b[31m"
#define C_GREEN "\x1b[32m"
#define C_WHITE "\x1b[0m"

typedef struct _record{
  int    id;
  char   *string_field;
  int    int_field;
  double double_field;
} Record;

//-Compares
int compare_int(const void *a, const void *b) {
    const Record *record1 = *(const Record **)a;
    const Record *record2 = *(const Record **)b;

    if (record1->int_field < record2->int_field)
        return -1;
    else if (record1->int_field > record2->int_field)
        return 1;
    else
        return 0;
}
int compare_double(const void *a, const void *b) {
    const Record *record1 = *(const Record **)a;
    const Record *record2 = *(const Record **)b;
    if (record1->double_field < record2->double_field)
        return -1;
    else if (record1->double_field > record2->double_field)
        return 1;
    else
        return 0;
}
int compare_strings(const void *a, const void *b) {
  const Record *record1 = *(const Record **)a;
  const Record *record2 = *(const Record **)b;
  return strcmp(record1->string_field, record2->string_field);
}


void file_check(FILE *file, char* path);

void read_infile(FILE *infile, Record **array_record_);
void write_outfile(FILE *outfile , Record **array_record_);

void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field);

void free_memory(Record **array);


int main(int argc, char *argv[]){
  if (argc < 5){
    printf("Please insert: make run src=/tmp/data/records.csv dest=/tmp/data/sorted.csv k=27 field=1\n");
    exit(EXIT_FAILURE);
  }
  int field = atoi(argv[4]);
  int k = atoi(argv[3]);

  if(k<0 || field>3|| field<1){
    printf("----k must be >=0 and field must be 1,2 or 3:\n");
    exit(EXIT_FAILURE);
  }

  FILE *infile = fopen(argv[1], "r");
  file_check(infile, argv[1]);
  FILE *outfile = fopen(argv[2], "w");
  file_check(outfile, argv[2]);
  
  
  printf("\n\tLoading Record from _file : %s...  \n", argv[1]);
  sort_records( infile, outfile, (size_t)k, (size_t)field);

  return EXIT_SUCCESS;
}

void file_check(FILE *file, char* path){
  if ( file == NULL){
    fprintf(stderr,"\n<%s>: unable to open _file.csv %s",NF,path);
    exit(EXIT_FAILURE);
  }
}

void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field){
  clock_t begin;
  Record **array_record_ = malloc(SIZE_FIELDS * sizeof(Record *));
  read_infile(infile, array_record_);

  begin = clock();
  switch (field){
    case 1:
      printf("\nSorting STRING\n");
      merge_binary_insertion_sort(array_record_, SIZE_FIELDS, sizeof(Record *), k, compare_strings);
      break;
    case 2:
      printf("\nSorting INT\n");
      merge_binary_insertion_sort(array_record_, SIZE_FIELDS, sizeof(Record *), k, compare_int);
      break;
    case 3:
      printf("\nSorting DOUBLE\n");
      merge_binary_insertion_sort(array_record_, SIZE_FIELDS, sizeof(Record *), k, compare_double);
      break;
  }

  printf(C_GREEN"<SORTED>"C_WHITE": TIME=%fs\n\n\tCreating file sorted... \n", (double)(clock()-begin)/CLOCKS_PER_SEC);
  write_outfile(outfile, array_record_);
  
  printf(C_GREEN"DONE"C_WHITE);
  free_memory(array_record_);
}


void read_infile(FILE *infile, Record **array_record_ ){
  int i = 0 ;
  char buffer[SIZE_BUFFER];
  char *read_index_field_   ;
  char *read_string_field_  ;
  char *read_integer_field_ ;
  char *read_double_field_  ;


  while (fgets(buffer, SIZE_BUFFER, infile ) != NULL 
          && i<SIZE_FIELDS
           ){
    array_record_[i] = malloc(sizeof(Record));
    if (array_record_[i] == NULL){
      fprintf(stderr, "\n\t<%s>: unable to allocate memory for read records",NF);
      exit(EXIT_FAILURE);
    }

    read_index_field_   = strtok(buffer, ",");
    read_string_field_  = strtok(NULL, ",");
    read_integer_field_ = strtok(NULL, ",");
    read_double_field_  = strtok(NULL, ",");
    
    array_record_[i]->id           = atoi(read_index_field_); 
    array_record_[i]->string_field = strdup(read_string_field_);
    array_record_[i]->int_field    = atoi(read_integer_field_);
    array_record_[i]->double_field = atof(read_double_field_);

    i++;
  }
  fclose(infile);
  printf(C_GREEN"\t\tSucess.\n"C_WHITE);
}

void write_outfile(FILE *outfile , Record **array_record_){
  for(int i=0; i<SIZE_FIELDS; i++){
    fprintf(outfile, "%d,%s,%d,%f\n", array_record_[i]->id,
                                      array_record_[i]->string_field, 
                                      array_record_[i]->int_field,
                                      array_record_[i]->double_field);
  }
  fclose(outfile);
}

void free_memory(Record **array){
  for(int i=0; i<SIZE_FIELDS; i++)  {
    free(array[i]->string_field);
    free(array[i]);
  }
  free(array);
}


