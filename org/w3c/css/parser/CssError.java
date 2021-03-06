//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Messages;

/**
 * This class represents an unknown error during the parse.
 *
 * @version $Revision$
 */
public class CssError {

	static final String parserError = "generator.unrecognize";

	/**
	 * The source file
	 */
	String sourceFile;

	/**
	 * The line number in the file
	 */
	int line;

	/**
	 * The error type, taken from
	 * the error, if defined.
	 */
	String type = null;
	/**
	 * The unknown error
	 */
	Throwable error;

	/**
	 * Create a new CssError
	 */
	public CssError() {
	}

	/**
	 * Create a new CssError
	 *
	 * @param sourceFile The source file
	 * @param line       The error line number
	 * @param error      The exception
	 */
	public CssError(String sourceFile, int line, Throwable error) {
		this.sourceFile = sourceFile;
		this.line = line;
		this.error = error;
	}

	/**
	 * Create a new CssError
	 *
	 * @param error The exception
	 */
	public CssError(Throwable error) {
		this.error = error;
	}

	/**
	 * Get the source file
	 */
	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * Get the source file
	 */
	public String getSourceFileEscaped() {
		return Messages.escapeString(sourceFile);
	}


	/**
	 * get the line number
	 */
	public int getLine() {
		return line;
	}

	/**
	 * get the unknown error
	 */
	public Throwable getException() {
		return error;
	}

	/**
	 * get the error type, null if undefined
	 */
	public String getType() {
		if (type == null) {
			if (error == null) {
				return null;
			}
			if (error instanceof InvalidParamException) {
				InvalidParamException exception = (InvalidParamException) error;
				type = exception.getErrorType();
			} else if (error instanceof CssParseException) {
				type = ((CssParseException) error).getErrorType();
			} else {
				type = error.getClass().getName();
			}
		}
		return type;
	}
}

