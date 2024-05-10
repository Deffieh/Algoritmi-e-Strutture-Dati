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
# define data directory
DATA		:= data/
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
	$(MD) $(DATA)

build/%.o: $(SRC)%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

all: directories main compile_test
	@echo "Compiling all complete!"

main: build/main.o build/skiplist.o $(COMMON_DEPS)
	$(CC) -o $(BIN)main  $(BUILD)main.o $(BUILD)skiplist.o 


compile_test: directories build/test.o build/skiplist.o build/unity.o $(COMMON_DEPS)
	$(CC) -o $(BIN)test $(BUILD)test.o $(BUILD)skiplist.o $(BUILD)unity.o



clean:
	$(RM) $(BUILD)* $(BIN)*
	@echo "Clean completed!!"

test: compile_test
	@echo "Start running!!\n\n"
	$(BIN)test
	@echo "\n\nEnd running!!"

run:
ifndef dic
	@echo "Missing file dictionary.txt use dic=''"
else ifndef cor
	@echo "Missing file correctme.csv use cor=''"
else ifndef max_height
	@echo "Missing max_heightuse max_height=''"
else
	@echo "\n\tSTART\n"
	$(BIN)main $(dic) $(cor) $(max_height)
	@echo "\n\tEND\n"
endif

# make run dic='data/dictionary.txt' cor='data/correctme.txt' max_height='10' 


