<?php

namespace EventBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class VolontaireType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('prenom',TextType::class,[
        'required'=>false,
    ])

            ->add('mail',TextType::class,[
                'required'=>false,
            ])
            ->add('tel',TextType::class,[
                'required'=>false,
            ])
            ->add('nom_event',TextType::class,[
                'required'=>false,
            ])
            ->add('presence', ChoiceType::class, [
                'choices'  => [
                    'Present' => "Present",
                    'Absent' => "Absent",

                ],
            ])
            ->add('GoCountMe', SubmitType::class);
            ;
    }/**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_volontaire';
    }


}
