# define the C compiler to use
CC = gcc

# define any compile-time flags
CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion

# define source directory
SRC		  := src/
# define bin directory
BIN		  := ./bin/
# define build directory
BUILD		:= build/
# Comand mkdir -p
MD	:= mkdir -p

# Comand remove files
RM = rm -f

# define include directory
INCLUDES = $(SRC)*.h
COMMON_DEPS = $(INCLUDES) Makefile



directories:
	$(MD) $(BIN)
	$(MD) $(BUILD)

build/%.o: $(SRC)%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

all: directories main compile_test
	@echo "Compiling all complete!"

main: build/main.o build/merge_binary_insertion_sort.o $(COMMON_DEPS)
	$(CC) -o $(BIN)main  $(BUILD)main.o $(BUILD)merge_binary_insertion_sort.o 


compile_test: directories build/test.o build/merge_binary_insertion_sort.o build/unity.o $(COMMON_DEPS)
	$(CC) -o $(BIN)test $(BUILD)test.o $(BUILD)merge_binary_insertion_sort.o $(BUILD)unity.o



clean:
	$(RM) $(BUILD)* $(BIN)*
	@echo "Clean completed!!"

test: compile_test
	@echo "Start running!!\n\n"
	$(BIN)test
	@echo "\n\nEnd running!!"

run:
ifndef src
	@echo "Missing file records.csv use src=''"
else ifndef dst
	@echo "Missing file sorted.csv use dst=''"
else ifndef k
	@echo "Missing size k use k=n n>=0"
else ifndef field
	@echo "Missing number field use field=n n=[1..3]"
else
	@echo "\n\tSTART\n"
	$(BIN)main $(src) $(dst) $(k) $(field)
	@echo "\n\tEND\n"
endif

#make run src="tmp/data/records.csv" dst="tmp/data/sorted.csv" k=27 field=1