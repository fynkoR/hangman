public class HangmanRenderer {
    private static final String[] PICTURES = {
            """
 +---- 
 |   |
 |   
 |  
 |
 -----
""",
              """
 +---- 
 |   |
 |   
 |  
 |
 -----
""",
            """
 +---- 
 |   |
 |   0
 |   |
 |   
 -----
""",
            """
 +---- 
 |   |
 |   0
 |  /|
 |   
 -----
""",
            """
 +---- 
 |   |
 |   0
 |  /||
 |   
 -----
""",
            """
 +---- 
 |   |
 |   0
 |  /||
 |  | 
 -----
""",
            """
 +---- 
 |   |
 |   0
 |  /||
 |  | |
 -----
"""
    };
    public static String render(int pictureNumber){
        return PICTURES[pictureNumber];
    }
}
