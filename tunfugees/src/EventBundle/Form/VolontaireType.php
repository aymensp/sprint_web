<?php

namespace EventBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class VolontaireType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')
            ->add('prenom',TextType::class,[
        'required'=>false,
    ])
            ->add('nomEvent',TextType::class,[
        'required'=>false,
    ])
            ->add('presence',TextType::class,[
        'required'=>true,
    ])
            ;
    }/**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_volontaire';
    }


}
