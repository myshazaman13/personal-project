/**
 * @file maze.c
 * @author Mysha Zaman
 * @brief Code for the maze game for COMP1921 Assignment 2
 * NOTE - You can remove or edit this file however you like - this is just a provided skeleton code
 * which may be useful to anyone who did not complete assignment 1.
 */
#include "maze.h"
/**
 * @brief Initialise a maze object - allocate memory and set attributes
 *
 * @param this pointer to the maze to be initialised
 * @param height height to allocate
 * @param width width to allocate
 * @return int 0 on success, 1 on fail
 */
int create_maze(maze *this, int height, int width)
{
    // Check if dimensions are within permitted range
    if (height < MIN_DIM || height > MAX_DIM || width < MIN_DIM || width > MAX_DIM) {
        return 1; // Return 1 on failure
    }

    // Allocate memory for maze map
    this->map = (char **)malloc(sizeof(char *) * height);
    if (this->map == NULL) {
        return 1; // Return 1 on failure
    }
    for (int i = 0; i < height; i++) {
        this->map[i] = (char *)malloc(sizeof(char) * width);
        if (this->map[i] == NULL) {
            // Free previously allocated memory
            for (int j = 0; j < i; j++) {
                free(this->map[j]);
            }
            free(this->map);
            return 1; 
        }
    }

    // Initialize maze dimensions
    this->height = height;
    this->width = width;

    // Initialize start and end coordinates
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (this->map[i][j] == 'S') {
                this->start.x = j;
                this->start.y = i;
            } else if (this->map[i][j] == 'E') {
                this->end.x = j;
                this->end.y = i;
            }
        }
    }

    return 0; 
}

/**
 * @brief Free the memory allocated to the maze struct
 *
 * @param this the pointer to the struct to free
 */
void free_maze(maze *this)
{
     // Free memory allocated for maze map
    if (this->map != NULL) {
        for (int i = 0; i < this->height; i++) {
            free(this->map[i]);
        }
        free(this->map);
        this->map = NULL;
    }
}


/**
 * @brief Validate and return the width of the mazefile
 *
 * @param file the file pointer to check
 * @return int 0 for error, or a valid width (5-100)
 */
int get_width(FILE *file)
{
    int width = 0;
    int ch;

    // Read characters from the file until newline or EOF
    while ((ch = fgetc(file)) != '\n' && ch != EOF) {
        width++;
    }

    // Reset the file pointer to the beginning of the file
    fseek(file, 0, SEEK_SET);

    
    if (width < MIN_DIM || width > MAX_DIM) {
        printf("Error: Invalid width\n");
        return 0; // Return 0 for error
    }

    return width; 

/**
 * @brief Validate and return the height of the mazefile
 *
 * @param file the file pointer to check
 * @return int 0 for error, or a valid height (5-100)
 */
 int get_height(FILE *file){
    int height = 0;
    int ch;

    // Read characters from the file until EOF
    while ((ch = fgetc(file)) != EOF) {
        if (ch == '\n') {
            height++;
        }
    }

    fseek(file, 0, SEEK_SET);

   
    if (height < MIN_DIM || height > MAX_DIM) {
        printf("Error: Invalid height\n");
        return 0; 
    }

    return height; // Return the height
}

/**
 * @brief read in a maze file into a struct
 *
 * @param this Maze struct to be used
 * @param file Maze file pointer
 * @return int 0 on success, 1 on fail
 */
int read_maze(maze *this, FILE *file)
{
    // Get maze width and height
    int width = get_width(file);
    int height = get_height(file);


    if (width == 0 || height == 0) {
        return 1; 
    }

    this->map = (char **)malloc(sizeof(char *) * height);
    if (this->map == NULL) {
        return 1; 
    }
    for (int i = 0; i < height; i++) {
        this->map[i] = (char *)malloc(sizeof(char) * (width + 1)); // +1 for null terminator
        if (this->map[i] == NULL) {
            // Free previously allocated memory
            for (int j = 0; j < i; j++) {
                free(this->map[j]);
            }
            free(this->map);
            return 1; 
        }
    }

    rewind(file);

    // Read characters from the file and populate the maze map
    int x = 0, y = 0;
    char ch;
    while ((ch = fgetc(file)) != EOF && y < height) {
        if (ch == '\n') {
            // Skip newline characters
            this->map[y][x] = '\0'; // Null terminate the string
            x = 0;
            y++;
        } else {
            // Store character in maze map
            this->map[y][x] = ch;
            x++;
        }
    }

    // Set maze dimensions
    this->width = width;
    this->height = height;

    // Initialize start and end coordinates
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (this->map[i][j] == 'S') {
                this->start.x = j;
                this->start.y = i;
            } else if (this->map[i][j] == 'E') {
                this->end.x = j;
                this->end.y = i;
            }
        }
    }

    return 0; 
}
/**
 * @brief Prints the maze out - code provided to ensure correct formatting
 *
 * @param this pointer to maze to print
 * @param player the current player location
 */
void print_maze(maze *this, coord *player)
{
    printf("\n");
    for (int i = 0; i < this->height; i++)
    {
        for (int j = 0; j < this->width; j++)
        {
            // decide whether player is on this spot or not
            if (player->x == j && player->y == i)
            {
                printf("X");
            }
            else
            {
                printf("%c", this->map[i][j]);
            }
        }
      
        printf("\n");
    }
}

/**
 * @brief Validates and performs a movement in a given direction
 *
 * @param this Maze struct
 * @param player The player's current position
 * @param direction The desired direction to move in
 */
void move(maze *this, coord *player, char direction)
{
    // Determine the new position based on the direction
    coord new_position = *player;
    switch (direction) {
        case 'W':
        case 'w':
            new_position.y--;
            break;
        case 'A':
        case 'a':
            new_position.x--;
        break;
        case 'S':
        case 's':
            new_position.y++;
            break;
        case 'D':
        case 'd':
            new_position.x++;
            break;
        default:
            printf("Invalid input. \n");
            break;
            return;
    }

    // Check if the new position is within the bounds of the maze
    if (new_position.x < 0 || new_position.x >= this->width || new_position.y < 0 || new_position.y >= this->height) {
        return;
    }

    // Check if the new position is a valid move (not a wall)
    if (this->map[new_position.y][new_position.x] != '#') {
        *player = new_position;
    }
}

/**
 * @brief Check whether the player has won and return a pseudo-boolean
 *
 * @param this current maze
 * @param player player position
 * @return int 0 for false, 1 for true
 */
int has_won(maze *this, coord *player)
{
    // Check if the player's position matches the end of the maze
    return (player->x == this->end.x && player->y == this->end.y);
}