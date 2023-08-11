package com.billdesk.usermanagement.exception;

public class UserAlreadyExist extends RuntimeException {

		private static final long serialVersionUID = 1L;
		private String message;
		public void UserAlreadyExistException(String message)
		{
			
			this.message=message;
		}
		public void UserAlreadyExistException() {}

	}


