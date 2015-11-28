<?php
/**
 * Created by PhpStorm.
 * User: Jorge
 * Date: 11/27/15
 * Time: 11:05 AM
 */

namespace AppBundle\Form\Type;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;

class ATMType extends AbstractType
{

    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('amount', 'money', array(
                'label'           => 'Amount to withdraw',
                'currency'        => 'USD',
                'invalid_message' => 'Please enter a numeric amount'))
            ->add('save', 'submit', array('label' => 'Withdraw'));
    }

    public function getName()
    {
        return 'atm';
    }
}