import React from 'react';
import { Col as ColBase } from 'antd';

import 'antd/lib/grid/style/index.less';

export declare type ColOptions = {
    flex?: string | number;
    offset?: number;
    order?: number;
    pull?: number;
    push?: number;
    span?: number;
};

export interface Props {
    children?: React.ReactNode;
    flex?: string | number;
    offset?: number;
    order?: number;
    pull?: number;
    push?: number;
    span?: number;
    xs?: number | ColOptions;
    sm?: number | ColOptions;
    md?: number | ColOptions;
    lg?: number | ColOptions;
    xl?: number | ColOptions;
    xxl?: number | ColOptions;
    className?: string;
}

const Col = ({ children, flex, offset, order, pull, push, span, xs, sm, md, lg, xl, xxl, className }: Props) => {
    return (
        <ColBase
            flex={flex}
            offset={offset}
            order={order}
            pull={pull}
            push={push}
            span={span}
            xs={xs}
            sm={sm}
            md={md}
            lg={lg}
            xl={xl}
            xxl={xxl}
            className={className}
        >
            {children}
        </ColBase>
    );
};

export default Col;
