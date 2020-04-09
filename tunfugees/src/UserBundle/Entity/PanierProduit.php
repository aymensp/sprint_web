<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PanierProduit
 *
 * @ORM\Table(name="panier_produit")
 * @ORM\Entity
 */
class PanierProduit
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
     * @var integer
     *
     * @ORM\Column(name="idProd", type="integer", nullable=false)
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
     * @var integer
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=255, nullable=false)
     */
    private $img;


}

