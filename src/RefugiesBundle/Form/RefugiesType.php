<?php

namespace RefugiesBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Vich\UploaderBundle\Form\Type\VichFileType;


class RefugiesType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom',TextType::class,[
                'required'=>false,
            ])
            ->add('prenom',TextType::class,[
                'required'=>false,
            ])
            ->add('age')
            ->add('pays',TextType::class,[
                'required'=>false,
            ])
            ->add('image');
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'RefugiesBundle\Entity\Refugies'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'refugiesbundle_refugies';
    }


}
