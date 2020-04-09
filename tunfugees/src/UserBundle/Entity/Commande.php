<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="etat_commande", type="string", length=11, nullable=false)
     */
    private $etatCommande;

    /**
     * @var string
     *
     * @ORM\Column(name="date_emission", type="string", length=30, nullable=false)
     */
    private $dateEmission;

    /**
     * @var string
     *
     * @ORM\Column(name="id_utilisateur", type="string", length=30, nullable=false)
     */
    private $idUtilisateur;

    /**
     * @var integer
     *
     * @ORM\Column(name="prixTotal", type="integer", nullable=false)
     */
    private $prixtotal;


}

