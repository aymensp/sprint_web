<?php

namespace ProduitBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\MoneyType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Vich\UploaderBundle\Form\Type\VichFileType;

class ProduitsType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nomProd',TextType::class,[
            'required'=>false,
        ])
            ->add('nomRef',TextType::class,[
                'required'=>false,
            ])
            ->add('description',TextType::class,[
                'required'=>false,
            ])
            ->add('prix',MoneyType::class,[
                'required'=>false,
            ])
            ->add('dispo',ChoiceType::class,
                array('choices'=>array
                (   'disponible'=>"disponible",
                    'non disponible'=>"non disponible",

                ),))
            ->add('produitPhoto', VichFileType::class, [
                'required' => false,
                'allow_delete' => true,
                'download_link' => true
            ])

            ->add('categorie',EntityType::class,array(
                    'class'=>'ProduitBundle:Categorie',
                    'multiple'=>false)
            );
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProduitBundle\Entity\Produits'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'produitbundle_produits';
    }


}
