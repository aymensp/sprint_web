<?php

namespace CampsBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Choice;

class CampsType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nbrmax')
            ->add('Categories',ChoiceType::class,
                array('choices'=>array
                (   'Infancy(0-3)'=>"Infancy(0-3)",
                    'Childhood(4-8)'=>"Childhood(4-8)",
                    'Puberty(9-13)'=>"Puberty(9-13)",
                    'Teenager(14-18)'=>"Teenager(14-18)",
                    'Adult(19-39)'=>"Adult(19-39)",
                    'Middle age(40-59)'=>"Middle age(40-59)",
                    'old age(60-99)'=>"old age(60-99)",

                ),))
            ->add('adresse');
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'CampsBundle\Entity\Camps'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'campsbundle_camps';
    }


}
