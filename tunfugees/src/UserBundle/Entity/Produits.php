<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produits
 *
 * @ORM\Table(name="produits", uniqueConstraints={@ORM\UniqueConstraint(name="nomProd", columns={"nomProd"})})
 * @ORM\Entity
 */
class Produits
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idProd", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idprod;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProd", type="string", length=30, nullable=false)
     */
    private $nomprod;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRef", type="string", length=30, nullable=false)
     */
    private $nomref;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=200, nullable=false)
     */
    private $img;

    /**
     * @var integer
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="dispo", type="string", length=30, nullable=true)
     */
    private $dispo;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=200, nullable=false)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="likes", type="integer", nullable=true)
     */
    private $likes;

    /**
     * @var integer
     *
     * @ORM\Column(name="views", type="integer", nullable=true)
     */
    private $views;


}

