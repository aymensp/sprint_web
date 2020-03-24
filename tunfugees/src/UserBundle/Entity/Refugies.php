<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Refugies
 *
 * @ORM\Table(name="refugies")
 * @ORM\Entity
 */
class Refugies
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idRef", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idref;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=30, nullable=false)
     */
    private $prenom;

    /**
     * @var integer
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="pays", type="string", length=30, nullable=false)
     */
    private $pays;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=200, nullable=false)
     */
    private $image;

    /**
     * @var integer
     *
     * @ORM\Column(name="idCamp", type="integer", nullable=false)
     */
    private $idcamp;


}

