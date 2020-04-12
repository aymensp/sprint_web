<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Donneur
 *
 * @ORM\Table(name="donneur")
 * @ORM\Entity
 */
class Donneur
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
     * @ORM\Column(name="cin", type="integer", nullable=false)
     */
    private $cin;

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
     * @var string
     *
     * @ORM\Column(name="mail", type="string", length=30, nullable=false)
     */
    private $mail;

    /**
     * @var integer
     *
     * @ORM\Column(name="don", type="integer", nullable=false)
     */
    private $don;

    /**
     * @var integer
     *
     * @ORM\Column(name="numcarte", type="integer", nullable=false)
     */
    private $numcarte;


}

