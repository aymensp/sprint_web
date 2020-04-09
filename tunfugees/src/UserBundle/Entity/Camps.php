<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Camps
 *
 * @ORM\Table(name="camps")
 * @ORM\Entity
 */
class Camps
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idCamp", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcamp;

    /**
     * @var integer
     *
     * @ORM\Column(name="nbrmax", type="integer", nullable=false)
     */
    private $nbrmax;

    /**
     * @var string
     *
     * @ORM\Column(name="nomCamp", type="string", length=30, nullable=false)
     */
    private $nomcamp;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=false)
     */
    private $adresse;

    /**
     * @var integer
     *
     * @ORM\Column(name="idRef", type="integer", nullable=false)
     */
    private $idref;


}

