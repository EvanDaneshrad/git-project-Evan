# git-project-Evan
init() is the first method I made for this project. Some of the problems I ran into while making this method were: deciding what it should return, deciding how to check if each file/directory already exists, and figuring out how to return the correct response

hashFile() is the method I made to generate the SHA-1 hashes for a file. It uses no helper methods. 

createBlob() is the method I made according to this description:
    This method that creates a blob for a given file
    It takes in the file path of an existing file
    It computes the SHA-1 hash of the file's contents
    Then creates a new file in git/objects/ with that hash as its filename
    Then it writes the original file's content into the blob, byte for byte, unchanged
    It returns the hashed file name of the new file created inside of git/objects/

updateIndexFile is the method I made according thos this description:
    This method adds a file's entry to git/index
    It takes in a String parameter filePath that represents, u guessed it!, the path of the file we're staging
    Each entry follows this format: Each line is the SHA-1 hash, one space, and the path relative to the project root. Nothing else goes on the line
    It returns the entry added to index