<?php
namespace classes\exception;

class NoteUnavailableException extends \Exception {
	const Default_Message = "Impossible to make a withdraw of this value:";

	public function __construct($message = null, $code = 0) {
		if($message!==null) {
			$this->message = $message;
		}
	}
}