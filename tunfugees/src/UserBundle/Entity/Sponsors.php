<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Sponsors
 *
 * @ORM\Table(name="sponsors")
 * @ORM\Entity
 */
class Sponsors
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idSponsor", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idsponsor;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=false)
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=11, nullable=false)
     */
    private $type;

    /**
     * @var integer
     *
     * @ORM\Column(name="numTel", type="integer", nullable=false)
     */
    private $numtel;


}

