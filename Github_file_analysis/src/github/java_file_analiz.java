package github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class java_file_analiz {
	public static void analyzeJavaFile(File file) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        int otherCommentLines = 0;
	        int codeLines = 0;
	        int totalLines = 0;
	        int functionCount = 0;
	        int javadocCommentLines = 0; 
	        
	        boolean inCommentBlock = false;
	        boolean inJavaDoc = false;

	        while ((line = reader.readLine()) != null) {
	            totalLines++;
	            line = line.trim();
	            //javadoc comments
	            if (line.startsWith("/**")) {
	                inJavaDoc = true;
	            }
	            if (inJavaDoc) {
	            	if(line.startsWith("*")&& !line.startsWith("*/")) {
	                javadocCommentLines++; 
	            	}
	            }
	            if (line.endsWith("*/") && inJavaDoc) {
	                inJavaDoc = false;
	            }
	            //other types of comments
	            if (line.startsWith("/*") && !line.endsWith("*/")) {
	                inCommentBlock = true;
	            }
	            if (inCommentBlock ) {
	                if (line.trim().startsWith("*") && !line.startsWith("*/") ) {
	                    otherCommentLines++; 
	                }
	            }
	            if (line.endsWith("*/") && inCommentBlock) {
	                inCommentBlock = false;
	            }
	            if (line.contains("//")) {
	                otherCommentLines++;
	            }
	            //lines of code
	            if (!inJavaDoc && !inCommentBlock && !line.isEmpty() && !line.trim().startsWith("//")&& !line.trim().endsWith("*/")) {
	                codeLines++;
	            }
	            //functions
	            if (line.contains("(") && line.contains(")") && line.endsWith("{")) {
	                functionCount++;
	            }
	        }
	        otherCommentLines = otherCommentLines-javadocCommentLines;
	        double commentDeviation = ((javadocCommentLines + otherCommentLines) * 0.8 / functionCount);
	        double codeDeviation = ((double)codeLines / functionCount) * 0.3;
	        double commentDeviationPercentage = ((100*commentDeviation) / codeDeviation) - 100;

	        System.out.println("Class: " + file.getName());
	        System.out.println("Number of Javadoc comments: " + javadocCommentLines);
	        System.out.println("Number of other types of comments: " + (otherCommentLines));
	        System.out.println("Number of code lines: " + codeLines);
	        System.out.println("LOC: " + totalLines);
	        System.out.println("Number of functions: " + functionCount);
	        System.out.printf("Comment Deviation Percentage: %.2f%%%n", commentDeviationPercentage);
	        System.out.println("-----------------------------------------");
	    }
	}	
}