#!/bin/bash

# Test 1: Valid maze file
./maze valid_maze.txt
status=$?
if [ $status -eq 0 ]; then
    echo "Test 1 Passed: The game ran successfully without any errors."
else
    echo "Test 1 Failed: The game encountered an error. Exit Status: $status"
fi

# Test 2: Missing maze file
./maze
if [ $? -eq 1 ]; then
    echo "Test 2 Passed: The program detected missing filename as expected."
else
    echo "Test 2 Failed: The program did not handle missing filename correctly."
fi

# Test 3: Invalid maze file format
./maze invalid_format_maze.txt
if [ $? -eq 1 ]; then
    echo "Test 3 Passed: The program detected invalid maze format as expected."
else
    echo "Test 3 Failed: The program did not handle invalid maze format correctly."
fi

# Test 4: Player moving within the maze successfully
./maze valid_maze.txt
echo "w"
if [ $? -eq 0 ]; then
    echo "Test 4 Passed: The player moved successfully within the maze."
else
    echo "Test 4 Failed: The player encountered an error while moving within the maze."
fi

# Test 5: Player walking into a wall
./maze valid_maze.txt
echo "d"
if [ $? -eq 1 ]; then
    echo "Test 5 Passed: The program detected player walking into a wall as expected."
else
    echo "Test 5 Failed: The program did not handle player walking into a wall correctly."
fi

# Test 6: Player reaching the exit
./maze exit_maze.txt
echo -e "w\nw\nw"
if [ $? -eq 0 ]; then
    echo "Test 6 Passed: The player reached the exit successfully."
else
    echo "Test 6 Failed: The player did not reach the exit as expected."
fi

# Test 7: Viewing the map
./maze valid_maze.txt
echo "m"
if [ $? -eq 0 ]; then
    echo "Test 7 Passed: The program displayed the map successfully."
else
    echo "Test 7 Failed: The program did not display the map correctly."
fi

# Test 8: Player moving off the edge of the map
./maze edge_of_map_maze.txt
echo "s"
if [ $? -eq 1 ]; then
    echo "Test 8 Passed: The program detected player moving off the edge of the map as expected."
else
    echo "Test 8 Failed: The program did not handle player moving off the edge of the map correctly."
fi

# Test 9: Invalid input for movement
./maze valid_maze.txt
echo "z"
if [ $? -eq 1 ]; then
    echo "Test 9 Passed: The program detected invalid input for movement as expected."
else
    echo "Test 9 Failed: The program did not handle invalid input for movement correctly."
fi

# Test 10: Player attempting to view the map before starting the game
./maze empty_maze.txt
echo "m"
if [ $? -eq 1 ]; then
    echo "Test 10 Passed: The program detected attempting to view the map before starting the game as expected."
else
    echo "Test 10 Failed: The program did not handle attempting to view the map before starting the game correctly."
fi

# Test 11: Player attempting to move after winning the game
./maze exit_maze.txt
echo -e "w\nm"
if [ $? -eq 1 ]; then
    echo "Test 11 Passed: The program detected attempting to move after winning the game as expected."
else
    echo "Test 11 Failed: The program did not handle attempting to move after winning the game correctly."
fi

# Test 12: Invalid maze with no exit
./maze no_exit_maze.txt
echo "w"
if [ $? -eq 1 ]; then
    echo "Test 12 Passed: The program detected an invalid maze with no exit as expected."
else
    echo "Test 12 Failed: The program did not handle an invalid maze with no exit correctly."
fi

# Test 13: Valid maze with no starting point
./maze no_start_maze.txt
if [ $? -eq 1 ]; then
    echo "Test 13 Passed: The program detected an error in the maze (no starting point) as expected."
else
    echo "Test 13 Failed: The program did not handle an error in the maze (no starting point) correctly."
fi

# Test 14: Attempting to view the map with an empty maze
./maze empty_maze.txt
echo "m"
if [ $? -eq 1 ]; then
    echo "Test 14 Passed: The program detected attempting to view the map with an empty maze as expected."
else
    echo "Test 14 Failed: The program did not handle attempting to view the map with an empty maze correctly."
fi

# Test 15: Invalid movement direction
./maze valid_maze.txt
echo "x"
if [ $? -eq 1 ]; then
    echo "Test 15 Passed: The program detected an invalid movement direction as expected."
else
    echo "Test 15 Failed: The program did not handle an invalid movement direction correctly."
fi

# Test 16: Maze with multiple 'S' (Multiple starting points)
./maze multiple_start_maze.txt
if [ $? -eq 1 ]; then
    echo "Test 16 Passed: The program detected an error in the maze (multiple starting points) as expected."
else
    echo "Test 16 Failed: The program did not handle an error in the maze (multiple starting points) correctly."
fi

# Test 17: Maze with incorrect row lengths
./maze invalid_row_lengths_maze.txt
if [ $? -eq 1 ]; then
    echo "Test 17 Passed: The program detected an error in the maze structure as expected."
else
    echo "Test 17 Failed: The program did not handle an error in the maze structure correctly."
fi

# Test 20: Moving without loading a maze
./maze
echo "w"
if [ $? -eq 1 ]; then
    echo "Test 20 Passed: The program detected attempting to move without loading a maze as expected."
else
    echo "Test 20 Failed: The program did not handle attempting to move without loading a maze correctly."
fi

# Test 22: Invalid character in maze
./maze invalid_char_maze.txt
if [ $? -eq 1 ]; then
    echo "Test 22 Passed: The program detected an error due to an invalid character in the maze as expected."
else
    echo "Test 22 Failed: The program did not handle an error due to an invalid character in the maze correctly."
fi

# Test 23: Moving player with an uninitialized maze
./maze uninitialized_maze.txt
echo "w"
if [ $? -eq 1 ]; then
    echo "Test 23 Passed: The program detected an error due to an uninitialized maze as expected."
else
    echo "Test 23 Failed: The program did not handle an error due to an uninitialized maze correctly."
fi
