<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Stock
 *
 * @ORM\Table(name="stockss")
 * @ORM\Entity
 */
class Stock
{
    /**
     * @var integer
     *
     * @ORM\Column(name="idStock", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idstock;

    /**
     * @var string
     *
     * @ORM\Column(name="typeStock", type="string", length=30, nullable=false)
     */
    private $typestock;

    /**
     * @var integer
     *
     * @ORM\Column(name="qteStock", type="integer", nullable=false)
     */
    private $qtestock;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProd", type="string", length=30, nullable=false)
     */
    private $nomprod;


}

