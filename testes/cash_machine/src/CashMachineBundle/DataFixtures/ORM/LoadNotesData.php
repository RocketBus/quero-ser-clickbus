<?php

namespace CashMachineBundle\DataFixtures\ORM;

use Doctrine\Common\DataFixtures\FixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use CashMachineBundle\Entity\Notes;

class LoadNotesData implements FixtureInterface
{
    public function load(ObjectManager $manager)
    {
        $note1 = new Notes();
        $note1->setNoteValue(100);
        $note1->setNotesAvailable(100);

        $manager->persist($note1);
        
        $note2 = new Notes();
        $note2->setNoteValue(50);
        $note2->setNotesAvailable(100);

        $manager->persist($note2);
        
        $note3 = new Notes();
        $note3->setNoteValue(20);
        $note3->setNotesAvailable(100);

        $manager->persist($note3);
        
        $note4 = new Notes();
        $note4->setNoteValue(10);
        $note4->setNotesAvailable(100);

        $manager->persist($note4);
        
        $manager->flush();
    }
}

