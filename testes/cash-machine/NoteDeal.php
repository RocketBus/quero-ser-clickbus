<?php
class NoteDeal
{
    public $denomination;
    public $count;
    
    function __construct($denomination = 0, $count = 0)
    {
        $this->denomination = $denomination;
        $this->count = $count;
    }
}
?>